package dev.da0hn.simplified.finance.core.domain;

import dev.da0hn.simplified.finance.core.domain.commands.NewDebitEntryCommand;
import dev.da0hn.simplified.finance.core.domain.validation.Validations;
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
import static dev.da0hn.simplified.finance.core.domain.validation.DomainValidationMessages.ENTRY_INSTALLMENT_DETAILS_NOT_NULL;
import static dev.da0hn.simplified.finance.core.domain.validation.DomainValidationMessages.ENTRY_ISSUED_AT_NOT_NULL;
import static dev.da0hn.simplified.finance.core.domain.validation.DomainValidationMessages.ENTRY_PAYMENT_METHOD_NOT_NULL;
import static dev.da0hn.simplified.finance.core.domain.validation.DomainValidationMessages.ENTRY_STATUS_NOT_NULL;
import static dev.da0hn.simplified.finance.core.domain.validation.DomainValidationMessages.ENTRY_TITLE_NOT_BLANK;
import static dev.da0hn.simplified.finance.core.domain.validation.DomainValidationMessages.ENTRY_TYPE_NOT_NULL;

public class Entry extends SelfValidating<Entry> {

  private final Long id;

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
  private final LocalDateTime issuedAt;

  @NotNull(message = ENTRY_INSTALLMENT_DETAILS_NOT_NULL)
  private final Optional<InstallmentDetails> installmentDetails;

  @NotNull(message = ENTRY_CATEGORIES_NOT_NULL)
  @NotEmpty(message = ENTRY_CATEGORIES_NOT_EMPTY)
  private final Set<@Valid Category> categories;

  private Entry(
    final Long id,
    final String title,
    final String description,
    final EntryType type,
    final EntryStatus status,
    final Amount amount,
    final PaymentMethod paymentMethod,
    final LocalDateTime createdAt,
    final LocalDateTime issuedAt,
    final Optional<InstallmentDetails> installmentDetails,
    final Set<Category> categories
  ) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.type = type;
    this.status = status;
    this.amount = amount;
    this.paymentMethod = paymentMethod;
    this.createdAt = createdAt;
    this.issuedAt = issuedAt;
    this.installmentDetails = installmentDetails;
    this.categories = categories != null ? new HashSet<>(categories) : new HashSet<>();
    this.validateSelf();
  }

  public static Entry debitEntry(final NewDebitEntryCommand command) {
    Validations.requireNonNull(command, "NewDebitEntryCommand");
    return new Entry(
      null,
      command.title(),
      command.description(),
      command.type(),
      command.status(),
      command.amount(),
      PaymentMethod.DEBIT,
      LocalDateTime.now(),
      command.issuedAt(),
      Optional.empty(),
      command.categories()
    );
  }

  public Optional<Long> maybeId() {
    return Optional.ofNullable(this.id);
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

  public LocalDateTime issuedAt() {
    return this.issuedAt;
  }

  public Optional<InstallmentDetails> installmentDetails() {
    return this.installmentDetails;
  }

  public Set<Category> categories() {
    return Collections.unmodifiableSet(this.categories);
  }

}
