package dev.da0hn.simplified.finance.core.domain.exceptions;

import java.io.Serial;

public class DataNotFoundException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = -6121912668279622037L;

  public DataNotFoundException(final String message) {
    super(message);
  }

  public DataNotFoundException(final String message, final Throwable cause) {
    super(message, cause);
  }

}
