package dev.da0hn.simplified.finance.core.domain.exceptions;

import java.util.function.Supplier;

public final class ExceptionFactory {

  private ExceptionFactory() { }

  /**
   * Create an IllegalArgumentException with the given message and arguments.
   *
   * @param message the message with placeholders for the arguments
   * @param args the arguments
   *
   * @return the IllegalArgumentException
   */
  public static IllegalArgumentException illegalArgument(final String message, final Object... args) {
    return new IllegalArgumentException(String.format(message, args));
  }

  /**
   * Create a supplier of IllegalArgumentException with the given message and arguments.
   *
   * @param message the message with placeholders for the arguments
   * @param args the arguments
   *
   * @return the supplier of IllegalArgumentException
   */
  public static Supplier<IllegalArgumentException> illegalArgumentSupplier(final String message, final Object... args) {
    return () -> new IllegalArgumentException(String.format(message, args));
  }

  /**
   * Create an IllegalStateException with the given message and arguments.
   *
   * @param message the message with placeholders for the arguments
   * @param args the arguments
   *
   * @return the IllegalStateException
   */
  public static IllegalStateException illegalState(final String message, final Object... args) {
    return new IllegalStateException(String.format(message, args));
  }

  /**
   * Create a supplier of IllegalStateException with the given message and arguments.
   *
   * @param message the message with placeholders for the arguments
   * @param args the arguments
   *
   * @return the supplier of IllegalStateException
   */
  public static Supplier<IllegalStateException> illegalStateSupplier(final String message, final Object... args) {
    return () -> new IllegalStateException(String.format(message, args));
  }

  /**
   * Create a DataNotFoundException with the given message and arguments.
   *
   * @param message the message with placeholders for the arguments
   * @param args the arguments
   *
   * @return the DataNotFoundException
   */
  public static DataNotFoundException dataNotFound(final String message, final Object... args) {
    return new DataNotFoundException(String.format(message, args));
  }

  /**
   * Create a supplier of DataNotFoundException with the given message and arguments.
   *
   * @param message the message with placeholders for the arguments
   * @param args the arguments
   *
   * @return the supplier of DataNotFoundException
   */
  public static Supplier<DataNotFoundException> dataNotFoundSupplier(final String message, final Object... args) {
    return () -> new DataNotFoundException(String.format(message, args));
  }

  /**
   * Create a {@link InvalidPreConditionException} with the given message and arguments.
   *
   * @param message the message with placeholders for the arguments
   * @param args the arguments
   *
   * @return the {@link InvalidPreConditionException}
   */
  public static InvalidPreConditionException invalidPreCondition(final String message, final Object... args) {
    return new InvalidPreConditionException(String.format(message, args));
  }

  public static Supplier<InvalidPreConditionException> invalidPreConditionSupplier(final String message, final Object... args) {
    return () -> new InvalidPreConditionException(String.format(message, args));
  }

}
