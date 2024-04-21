package dev.da0hn.simplified.finance.core.domain;

import dev.da0hn.simplified.finance.core.domain.exceptions.DomainValidationException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public final class Amount {

  private final BigDecimal value;

  private Amount(final BigDecimal value) {
    Objects.requireNonNull(value, "value is required");
    if (value.compareTo(BigDecimal.ZERO) <= 0) {
      throw new DomainValidationException("Amount value must be greater than zero");
    }
    this.value = value.setScale(2, RoundingMode.HALF_EVEN);
  }

  public static Amount of(final BigDecimal value) {
    return new Amount(value);
  }

  public static Amount of(final long value) {
    return new Amount(BigDecimal.valueOf(value));
  }

  public static Amount of(final double value) {
    return new Amount(BigDecimal.valueOf(value));
  }

  public BigDecimal value() {
    return this.value;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this.value);
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (!(o instanceof final Amount amount)) return false;

    return Objects.equals(this.value, amount.value);
  }

}
