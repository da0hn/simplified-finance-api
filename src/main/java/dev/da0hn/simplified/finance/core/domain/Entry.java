package dev.da0hn.simplified.finance.core.domain;

import dev.da0hn.simplified.finance.core.domain.commands.NewCreditExpenseEntryCommand;
import dev.da0hn.simplified.finance.core.domain.commands.NewDebitExpenseEntryCommand;
import dev.da0hn.simplified.finance.core.domain.commands.NewOneTimePaymentCreditExpenseEntry;
import dev.da0hn.simplified.finance.core.domain.enums.EntryStatus;
import dev.da0hn.simplified.finance.core.domain.enums.EntryType;
import dev.da0hn.simplified.finance.core.domain.enums.PaymentMethod;
import dev.da0hn.simplified.finance.core.domain.validation.SelfValidating;
import dev.da0hn.simplified.finance.core.domain.validation.Validations;
import dev.da0hn.simplified.finance.core.domain.valueobjects.Amount;
import dev.da0hn.simplified.finance.core.domain.valueobjects.EntryId;
import dev.da0hn.simplified.finance.core.domain.valueobjects.IssuedAt;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static dev.da0hn.simplified.finance.core.domain.validation.DomainValidationMessages.ENTRY_AMOUNT_NOT_NULL;
import static dev.da0hn.simplified.finance.core.domain.validation.DomainValidationMessages.ENTRY_CATEGORIES_NOT_EMPTY;
import static dev.da0hn.simplified.finance.core.domain.validation.DomainValidationMessages.ENTRY_CATEGORIES_NOT_NULL;
import static dev.da0hn.simplified.finance.core.domain.validation.DomainValidationMessages.ENTRY_CREATED_AT_NOT_NULL;
import static dev.da0hn.simplified.finance.core.domain.validation.DomainValidationMessages.ENTRY_DESCRIPTION_NOT_BLANK;
import static dev.da0hn.simplified.finance.core.domain.validation.DomainValidationMessages.ENTRY_ID_NOT_NULL;
import static dev.da0hn.simplified.finance.core.domain.validation.DomainValidationMessages.ENTRY_ISSUED_AT_NOT_NULL;
import static dev.da0hn.simplified.finance.core.domain.validation.DomainValidationMessages.ENTRY_PAYMENT_METHOD_NOT_NULL;
import static dev.da0hn.simplified.finance.core.domain.validation.DomainValidationMessages.ENTRY_STATUS_NOT_NULL;
import static dev.da0hn.simplified.finance.core.domain.validation.DomainValidationMessages.ENTRY_TITLE_NOT_BLANK;
import static dev.da0hn.simplified.finance.core.domain.validation.DomainValidationMessages.ENTRY_TYPE_NOT_NULL;

public class Entry extends SelfValidating<Entry> {

  @NotNull(message = ENTRY_ID_NOT_NULL)
  private final EntryId entryId;

  @NotBlank(message = ENTRY_TITLE_NOT_BLANK)
  private final String title;

  @NotBlank(message = ENTRY_DESCRIPTION_NOT_BLANK)
  private final String description;

  @NotNull(message = ENTRY_TYPE_NOT_NULL)
  private final EntryType type;

  @NotNull(message = ENTRY_STATUS_NOT_NULL)
  private final EntryStatus status;

  @NotNull(message = ENTRY_AMOUNT_NOT_NULL)
  private final Amount amount;

  @NotNull(message = ENTRY_PAYMENT_METHOD_NOT_NULL)
  private final PaymentMethod paymentMethod;

  @NotNull(message = ENTRY_CREATED_AT_NOT_NULL)
  private final LocalDateTime createdAt;

  @NotNull(message = ENTRY_ISSUED_AT_NOT_NULL)
  private final IssuedAt issuedAt;

  private final Optional<@Valid InstallmentDetail> installmentDetail;

  @NotNull(message = ENTRY_CATEGORIES_NOT_NULL)
  @NotEmpty(message = ENTRY_CATEGORIES_NOT_EMPTY)
  private final Set<@Valid Category> categories;

  private Entry(
    final EntryId entryId,
    final String title,
    final String description,
    final EntryType type,
    final EntryStatus status,
    final Amount amount,
    final PaymentMethod paymentMethod,
    final LocalDateTime createdAt,
    final IssuedAt issuedAt,
    final Optional<@Valid InstallmentDetail> installmentDetail,
    final Set<Category> categories
  ) {
    this.entryId = entryId;
    this.title = title;
    this.description = description;
    this.type = type;
    this.status = status;
    this.amount = amount;
    this.paymentMethod = paymentMethod;
    this.createdAt = createdAt;
    this.issuedAt = issuedAt;
    this.installmentDetail = installmentDetail;
    this.categories = categories != null ? new HashSet<>(categories) : new HashSet<>();
    this.validateSelf();
  }

  public static Entry debitExpenseEntry(final NewDebitExpenseEntryCommand command) {
    Validations.requireNonNull(command, "NewDebitEntryCommand");
    return new Entry(
      EntryId.newInstance(),
      command.title(),
      command.description(),
      EntryType.EXPENSE,
      command.status(),
      command.amount(),
      PaymentMethod.DEBIT_CARD,
      LocalDateTime.now(),
      command.issuedAt(),
      Optional.empty(),
      command.categories()
    );
  }

  public static Entry creditExpenseEntry(final NewCreditExpenseEntryCommand command) {
    Validations.requireNonNull(command, "NewCreditExpenseEntryCommand");
    final var entryId = EntryId.newInstance();
    return new Entry(
      entryId,
      command.title(),
      command.description(),
      EntryType.EXPENSE,
      command.status(),
      command.amount(),
      PaymentMethod.CREDIT_CARD,
      LocalDateTime.now(),
      command.issuedAt(),
      Optional.of(InstallmentDetail.newInstallmentDetail(entryId, command.installmentNumber(), command.futureExpenseEntry())),
      command.categories()
    );
  }

  public static Entry oneTimePaymentCreditExpenseEntry(final NewOneTimePaymentCreditExpenseEntry command) {
    Validations.requireNonNull(command, "NewOneTimePaymentCreditExpenseEntry");
    return new Entry(
      EntryId.newInstance(),
      command.title(),
      command.description(),
      EntryType.EXPENSE,
      EntryStatus.PAID,
      command.amount(),
      PaymentMethod.CREDIT_CARD,
      LocalDateTime.now(),
      command.issuedAt(),
      Optional.empty(),
      command.categories()
    );
  }

  public EntryId entryId() {
    return this.entryId;
  }

  public String title() {
    return this.title;
  }

  public String description() {
    return this.description;
  }

  public EntryType type() {
    return this.type;
  }

  public EntryStatus status() {
    return this.status;
  }

  public Amount amount() {
    return this.amount;
  }

  public PaymentMethod paymentMethod() {
    return this.paymentMethod;
  }

  public LocalDateTime createdAt() {
    return this.createdAt;
  }

  public IssuedAt issuedAt() {
    return this.issuedAt;
  }

  public Optional<InstallmentDetail> installmentDetail() {
    return this.installmentDetail;
  }

  public Set<Category> categories() {
    return Collections.unmodifiableSet(this.categories);
  }

}
