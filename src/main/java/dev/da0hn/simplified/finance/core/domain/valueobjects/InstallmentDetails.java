package dev.da0hn.simplified.finance.core.domain.valueobjects;

import dev.da0hn.simplified.finance.core.domain.validation.SelfValidating;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.YearMonth;

public class InstallmentDetails extends SelfValidating<InstallmentDetails> {

  @NotNull
  private final Long entryId;

  @NotNull
  @Positive
  private final Long installments;

  @NotNull
  private final Amount amount;

  @NotNull
  private final YearMonth date;

  private InstallmentDetails(
    final Long entryId,
    final Long installments,
    final Amount amount,
    final YearMonth date
  ) {
    this.entryId = entryId;
    this.installments = installments;
    this.amount = amount;
    this.date = date;
    this.validateSelf();
  }

}
