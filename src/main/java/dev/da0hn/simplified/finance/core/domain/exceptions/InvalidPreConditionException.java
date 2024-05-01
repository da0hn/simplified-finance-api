package dev.da0hn.simplified.finance.core.domain.exceptions;

import java.io.Serial;

public class InvalidPreConditionException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = -6121912668279622037L;

  public InvalidPreConditionException(final String message) {
    super(message);
  }

  public InvalidPreConditionException(final String message, final Throwable cause) {
    super(message, cause);
  }

}
