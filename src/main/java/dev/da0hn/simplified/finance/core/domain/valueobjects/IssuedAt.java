package dev.da0hn.simplified.finance.core.domain.valueobjects;

import dev.da0hn.simplified.finance.core.domain.validation.DomainValidationMessages;
import dev.da0hn.simplified.finance.core.domain.validation.Validations;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.Objects;

public class IssuedAt {

  @NotNull(message = DomainValidationMessages.ISSUED_AT_DATE_NOT_NULL)
  private final LocalDateTime date;

  @NotNull(message = DomainValidationMessages.ISSUED_AT_REFERENCE_MONTH_NOT_NULL)
  private final YearMonth referenceMonth;

  private IssuedAt(final LocalDateTime date, final YearMonth referenceMonth) {
    this.date = date;
    this.referenceMonth = referenceMonth;
  }

  public static IssuedAt of(final LocalDateTime date) {
    Validations.requireNonNull(date, "IssuedAt");
    return new IssuedAt(date, YearMonth.from(date));
  }

  public LocalDateTime date() {
    return this.date;
  }

  public YearMonth referenceMonth() {
    return this.referenceMonth;
  }

  @Override
  public int hashCode() {
    int result = Objects.hashCode(this.date);
    result = 31 * result + Objects.hashCode(this.referenceMonth);
    return result;
  }

  @Override
  public final boolean equals(final Object o) {
    if (this == o) return true;
    if (!(o instanceof final IssuedAt issuedAt)) return false;

    return Objects.equals(this.date, issuedAt.date) && Objects.equals(this.referenceMonth, issuedAt.referenceMonth);
  }

  @Override
  public String toString() {
    return "IssuedAt{date=" + this.date + '}';
  }

  public IssuedAt plusMonths(final long months) {
    final var newDate = this.date.plusMonths(months);
    return IssuedAt.of(newDate);
  }

}
