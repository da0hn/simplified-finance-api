package dev.da0hn.simplified.finance.core.domain;

import dev.da0hn.simplified.finance.core.domain.validation.DomainValidationMessages;
import dev.da0hn.simplified.finance.core.domain.validation.SelfValidating;
import dev.da0hn.simplified.finance.core.domain.valueobjects.EntryId;
import jakarta.validation.constraints.NotNull;

public class InstallmentDetail extends SelfValidating<InstallmentDetail> implements Comparable<InstallmentDetail> {

  @NotNull(message = DomainValidationMessages.ENTRY_ID_NOT_NULL)
  private final EntryId entryId;

  @NotNull(message = DomainValidationMessages.INSTALLMENT_DETAIL_NUMBER_NOT_NULL)
  private final Long installmentNumber;

  @NotNull(message = DomainValidationMessages.INSTALLMENT_FUTURE_EXPENSE_ENTRY_NOT_NULL)
  private final FutureExpenseEntry futureExpenseEntry;

  private InstallmentDetail(
    final EntryId entryId,
    final Long installmentNumber,
    final FutureExpenseEntry futureExpenseEntry
  ) {
    this.entryId = entryId;
    this.installmentNumber = installmentNumber;
    this.futureExpenseEntry = futureExpenseEntry;
  }

  public static InstallmentDetail newInstallmentDetail(
    final EntryId entryId,
    final Long installmentNumber,
    final FutureExpenseEntry futureExpenseEntry
  ) {
    return new InstallmentDetail(entryId, installmentNumber, futureExpenseEntry);
  }

  public EntryId entryId() {
    return this.entryId;
  }

  public Long installmentNumber() {
    return this.installmentNumber;
  }

  public FutureExpenseEntry futureExpenseEntry() {
    return this.futureExpenseEntry;
  }

  @Override
  public int compareTo(final InstallmentDetail other) {
    if (other == null) {
      return 1;
    }
    return this.installmentNumber.compareTo(other.installmentNumber);
  }

}
