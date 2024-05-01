package dev.da0hn.simplified.finance.core.usecases.impl;

import dev.da0hn.simplified.finance.core.ports.api.usecases.SearchCategoriesUseCase;
import dev.da0hn.simplified.finance.core.ports.spi.repositories.CategoryRepository;
import dev.da0hn.simplified.finance.core.shared.annotations.UseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.stream.Collectors;

@Slf4j
@UseCase
@AllArgsConstructor
public class SearchCategoriesUseCaseImpl implements SearchCategoriesUseCase {

  private final CategoryRepository categoryRepository;

  @Override
  public Output execute(final Input input) {
    log.info("method=execute(input={})", input);
    final var categories = this.categoryRepository.searchBy(input.queryName(), input.queryDescription(), input.queryText());
    if (log.isDebugEnabled()) {
      log.debug("method=categoryRepository.searchBy(input={}) => {}", input, categories);
    }
    return categories.stream()
      .map(category -> new OutputCategory(category.categoryId().value(), category.name(), category.description()))
      .collect(Collectors.collectingAndThen(Collectors.toList(), Output::new));
  }

}
