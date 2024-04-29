package dev.da0hn.simplified.finance.core.domain.valueobjects;

import dev.da0hn.simplified.finance.core.domain.validation.DomainValidationMessages;
import dev.da0hn.simplified.finance.core.domain.validation.SelfValidating;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.Objects;

public class InstallmentQuantity extends SelfValidating<InstallmentQuantity> {

  @NotNull(message = DomainValidationMessages.INSTALLMENT_NOT_NULL)
  @Positive(message = DomainValidationMessages.INSTALLMENT_POSITIVE)
  private final Long value;

  private InstallmentQuantity(final Long value) {
    this.value = value;
    this.validateSelf();
  }

  public static InstallmentQuantity of(final Long value) {
    return new InstallmentQuantity(value);
  }

  public Long value() {
    return this.value;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this.value);
  }

  @Override
  public final boolean equals(final Object o) {
    if (this == o) return true;
    if (!(o instanceof final InstallmentQuantity that)) return false;

    return Objects.equals(this.value, that.value);
  }

  @Override
  public String toString() {
    return this.value.toString();
  }

}
