package dev.da0hn.simplified.finance.application.service.impl;

import dev.da0hn.simplified.finance.application.controllers.dto.CreateCategoryParameters;
import dev.da0hn.simplified.finance.application.controllers.dto.ResourceCreated;
import dev.da0hn.simplified.finance.application.controllers.dto.ResourceSummary;
import dev.da0hn.simplified.finance.application.controllers.dto.SearchCategoriesParameters;
import dev.da0hn.simplified.finance.application.service.CategoryService;
import dev.da0hn.simplified.finance.core.ports.api.usecases.CreateCategoryUseCase;
import dev.da0hn.simplified.finance.core.ports.api.usecases.SearchCategoriesUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

  private final CreateCategoryUseCase createCategoryUseCase;

  private final SearchCategoriesUseCase searchCategoriesUseCase;

  @Override
  public ResourceCreated createCategory(final CreateCategoryParameters parameter) {
    log.info("method=createCategory(parameter={})", parameter);

    final var input = CreateCategoryUseCase.Input.builder()
      .categoryName(parameter.categoryName())
      .categoryDescription(parameter.categoryDescription())
      .build();

    final var output = this.createCategoryUseCase.execute(input);

    return new ResourceCreated(output.categoryId());
  }

  @Override
  public List<ResourceSummary> searchCategories(final SearchCategoriesParameters parameters) {
    log.info("method=searchCategories(parameters={})", parameters);

    final var input = SearchCategoriesUseCase.Input.builder()
      .queryId(parameters.id())
      .queryName(parameters.name())
      .queryDescription(parameters.description())
      .queryText(parameters.text())
      .build();

    final var output = this.searchCategoriesUseCase.execute(input);

    return output.categories().stream()
      .map(category -> new ResourceSummary(category.categoryId(), category.categoryName()))
      .toList();
  }

}
