package dev.da0hn.simplified.finance.core.domain.exceptions;

import java.io.Serial;

public class DomainValidationException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = -4896840167118463118L;

  public DomainValidationException(final String message) {
    super(message);
  }

  public DomainValidationException(final String message, final Throwable cause) {
    super(message, cause);
  }

}
