package dev.da0hn.simplified.finance.core.usecases.impl;

import dev.da0hn.simplified.finance.core.domain.Category;
import dev.da0hn.simplified.finance.core.domain.exceptions.ExceptionFactory;
import dev.da0hn.simplified.finance.core.domain.validation.Validations;
import dev.da0hn.simplified.finance.core.ports.api.usecases.CreateCategoryUseCase;
import dev.da0hn.simplified.finance.core.ports.spi.repositories.CategoryRepository;
import dev.da0hn.simplified.finance.core.shared.annotations.UseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UseCase
@AllArgsConstructor
public class CreateCategoryUseCaseImpl implements CreateCategoryUseCase {

  private final CategoryRepository categoryRepository;

  @Override
  public Output execute(final Input input) {
    log.info("method=execute(input={})", input);
    Validations.requireNotPresent(
      this.categoryRepository.findByName(input.categoryName()),
      ExceptionFactory.illegalArgumentSupplier("Category with name %s already exists", input.categoryName())
    );

    final var category = Category.newCategory(input.categoryName(), input.categoryDescription());

    this.categoryRepository.create(category);

    if (log.isDebugEnabled()) {
      log.debug("method=categoryRepository.create(category={})", category);
    }

    return Output.builder()
      .categoryId(category.categoryId().value())
      .build();
  }

}
