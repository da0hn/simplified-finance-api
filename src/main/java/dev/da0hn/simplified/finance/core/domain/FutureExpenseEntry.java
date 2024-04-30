package dev.da0hn.simplified.finance.core.domain;

import dev.da0hn.simplified.finance.core.domain.commands.NewCreditExpenseEntryCommand;
import dev.da0hn.simplified.finance.core.domain.commands.NewFutureExpenseCommand;
import dev.da0hn.simplified.finance.core.domain.enums.EntryStatus;
import dev.da0hn.simplified.finance.core.domain.exceptions.DomainValidationException;
import dev.da0hn.simplified.finance.core.domain.validation.DomainValidationMessages;
import dev.da0hn.simplified.finance.core.domain.validation.SelfValidating;
import dev.da0hn.simplified.finance.core.domain.validation.Validations;
import dev.da0hn.simplified.finance.core.domain.valueobjects.Amount;
import dev.da0hn.simplified.finance.core.domain.valueobjects.FutureExpenseEntryId;
import dev.da0hn.simplified.finance.core.domain.valueobjects.InstallmentQuantity;
import dev.da0hn.simplified.finance.core.domain.valueobjects.IssuedAt;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class FutureExpenseEntry extends SelfValidating<FutureExpenseEntry> {

  @NotNull(message = DomainValidationMessages.FUTURE_EXPENSE_ENTRY_ID_NOT_NULL)
  private final FutureExpenseEntryId futureExpenseEntryId;

  @NotBlank(message = DomainValidationMessages.FUTURE_EXPENSE_ENTRY_TITLE_NOT_BLANK)
  private final String title;

  @NotBlank(message = DomainValidationMessages.FUTURE_EXPENSE_ENTRY_DESCRIPTION_NOT_BLANK)
  private final String description;

  @NotNull(message = DomainValidationMessages.FUTURE_EXPENSE_ENTRY_INSTALLMENT_QUANTITY_NOT_NULL)
  private final InstallmentQuantity installmentQuantity;

  @NotNull(message = DomainValidationMessages.FUTURE_EXPENSE_ENTRY_TOTAL_AMOUNT_NOT_NULL)
  private final Amount totalAmount;

  @NotNull(message = DomainValidationMessages.FUTURE_EXPENSE_ENTRY_PARTIAL_AMOUNT_NOT_NULL)
  private final Amount partialAmount;

  @NotNull(message = DomainValidationMessages.FUTURE_EXPENSE_ENTRY_ISSUED_AT_NOT_NULL)
  private final IssuedAt issuedAt;

  @NotNull(message = DomainValidationMessages.FUTURE_EXPENSE_ENTRY_CREATED_AT_NOT_NULL)
  private final LocalDateTime createdAt;

  @NotNull(message = DomainValidationMessages.FUTURE_EXPENSE_ENTRY_CHILDREN_NOT_NULL)
  private final Set<@Valid Entry> entries;

  private FutureExpenseEntry(
    final FutureExpenseEntryId futureExpenseEntryId,
    final String title,
    final String description,
    final InstallmentQuantity installmentQuantity,
    final Amount totalAmount,
    final Amount partialAmount,
    final IssuedAt issuedAt,
    final LocalDateTime createdAt,
    final Set<Entry> entries
  ) {
    this.futureExpenseEntryId = futureExpenseEntryId;
    this.title = title;
    this.description = description;
    this.installmentQuantity = installmentQuantity;
    this.totalAmount = totalAmount;
    this.partialAmount = partialAmount;
    this.issuedAt = issuedAt;
    this.createdAt = createdAt;
    this.entries = new TreeSet<>(Comparator.comparing(
      e -> e.installmentDetail().map(InstallmentDetail::installmentNumber).orElse(-1L)
    ));
    this.entries.addAll(entries);
  }

  public static FutureExpenseEntry newFutureExpense(final NewFutureExpenseCommand command) {
    Validations.requireNonNull(command, "NewFutureExpenseCommand");
    Validations.requireNonNull(command.installmentQuantity(), "InstallmentQuantity");
    Validations.requireNonNull(command.totalAmount(), "TotalAmount");
    Validations.requireNonEmpty(
      command.categories(),
      () -> new DomainValidationException(DomainValidationMessages.FUTURE_EXPENSE_ENTRY_CATEGORIES_NOT_EMPTY)
    );

    final var partialAmount = command.totalAmount().divideBy(command.installmentQuantity().value());

    final var futureExpenseEntry = new FutureExpenseEntry(
      FutureExpenseEntryId.newInstance(),
      command.title(),
      command.description(),
      command.installmentQuantity(),
      command.totalAmount(),
      partialAmount,
      command.issuedAt(),
      LocalDateTime.now(),
      HashSet.newHashSet(0)
    );

    futureExpenseEntry.generateEntries(command.categories());

    return futureExpenseEntry;
  }

  public FutureExpenseEntryId futureExpenseEntryId() {
    return this.futureExpenseEntryId;
  }

  public String title() {
    return this.title;
  }

  public String description() {
    return this.description;
  }

  public InstallmentQuantity installments() {
    return this.installmentQuantity;
  }

  public Amount totalAmount() {
    return this.totalAmount;
  }

  public Amount partialAmount() {
    return this.partialAmount;
  }

  public IssuedAt issuedAt() {
    return this.issuedAt;
  }

  public LocalDateTime createdAt() {
    return this.createdAt;
  }

  public Set<Entry> entries() {
    return Collections.unmodifiableSet(this.entries);
  }

  private void generateEntries(final Set<Category> categories) {
    final var entries = LongStream.rangeClosed(1, this.installments().value())
      .mapToObj(installmentNumber -> {
        if (installmentNumber == 1) {
          return new NewCreditExpenseEntryCommand(
            this,
            this.title(),
            this.description(),
            this.partialAmount,
            EntryStatus.PAID,
            this.issuedAt(),
            1L,
            categories
          );
        }
        return new NewCreditExpenseEntryCommand(
          this,
          this.title(),
          this.description(),
          this.partialAmount,
          EntryStatus.PENDING,
          this.issuedAt().plusMonths(installmentNumber),
          installmentNumber,
          categories
        );
      })
      .map(Entry::creditExpenseEntry)
      .collect(Collectors.toSet());
    this.entries.addAll(entries);
  }

}
