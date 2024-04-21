package dev.da0hn.simplified.finance.core.domain.exceptions;

import java.io.Serial;
import java.util.Collections;
import java.util.List;

public class DomainConstraintViolationException extends DomainValidationException {

  @Serial
  private static final long serialVersionUID = 4018277325175004909L;

  private final List<Violation> violations;

  public DomainConstraintViolationException(final List<Violation> violations) {
    super(
      violations.stream()
        .map(violation -> String.format(
          "Field '%s' has the following constraint violations: %s",
          violation.field(),
          violation.message()
        ))
        .reduce((a, b) -> a + "\n" + b)
        .orElse("No constraint violations found")
    );
    this.violations = violations;
  }

  public DomainConstraintViolationException(final List<Violation> violations, final Throwable cause) {
    super(
      violations.stream()
        .map(violation -> String.format(
          "Field '%s' has the following constraint violations: %s",
          violation.field(),
          violation.message()
        ))
        .reduce((a, b) -> a + "\n" + b)
        .orElseThrow(() -> new IllegalArgumentException("No constraint violations found")),
      cause
    );
    this.violations = violations;
  }

  public record Violation(String field, String message) {
  }

  public List<Violation> getViolations() {
    return Collections.unmodifiableList(this.violations);
  }

}
