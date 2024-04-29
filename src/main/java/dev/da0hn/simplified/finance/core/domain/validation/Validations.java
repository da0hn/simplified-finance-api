package dev.da0hn.simplified.finance.core.domain.validation;

import java.util.Collection;
import java.util.function.Supplier;

import static dev.da0hn.simplified.finance.core.domain.validation.DomainValidationMessages.NON_NULL_ARGUMENT;

public final class Validations {

  private Validations() { }

  public static void requireNonNull(
    final Object object,
    final String parameterName
  ) throws IllegalArgumentException {
    if (object == null) {
      throw new IllegalArgumentException(
        String.format(NON_NULL_ARGUMENT, parameterName)
      );
    }
  }

  public static <T, X extends RuntimeException> void requireNonEmpty(
    final Collection<T> collection,
    final Supplier<X> exceptionSupplier
  ) {
    if (collection == null || collection.isEmpty()) {
      throw exceptionSupplier.get();
    }

  }

}
