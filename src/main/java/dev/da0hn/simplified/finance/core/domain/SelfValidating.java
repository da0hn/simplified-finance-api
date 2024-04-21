package dev.da0hn.simplified.finance.core.domain;

import dev.da0hn.simplified.finance.core.domain.exceptions.DomainConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import static dev.da0hn.simplified.finance.core.domain.exceptions.DomainConstraintViolationException.Violation;

public abstract class SelfValidating<T> {

  private final Validator validator;

  protected SelfValidating() {
    try (final var validatorFactory = Validation.buildDefaultValidatorFactory()) {
      this.validator = validatorFactory.getValidator();
    }
  }

  protected void validateSelf() {
    final var violations = this.validator.validate(this);

    if (violations.isEmpty()) return;
    throw new DomainConstraintViolationException(
      violations.stream()
        .map(violation -> new Violation(
               violation.getPropertyPath().toString(),
               violation.getMessage()
             )
        ).toList()
    );
  }

}
