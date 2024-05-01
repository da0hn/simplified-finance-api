package dev.da0hn.simplified.finance.core.domain.validation;

import dev.da0hn.simplified.finance.core.domain.exceptions.ExceptionFactory;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Supplier;

import static dev.da0hn.simplified.finance.core.domain.validation.DomainValidationMessages.NON_NULL_ARGUMENT;

public final class Validations {

  private Validations() { }

  public static void requireNonNull(
    final Object object,
    final String parameterName
  ) throws IllegalArgumentException {
    if (object == null) {
      throw ExceptionFactory.illegalArgument(NON_NULL_ARGUMENT, parameterName);
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

  public static <T, X extends RuntimeException> void requireNotPresent(
    final Optional<T> maybeValue,
    final Supplier<X> exceptionSupplier
  ) {
    maybeValue.map(value -> {
      throw exceptionSupplier.get();
    });
  }

}
