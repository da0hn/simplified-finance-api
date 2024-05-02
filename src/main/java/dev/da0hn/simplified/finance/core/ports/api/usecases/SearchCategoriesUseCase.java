package dev.da0hn.simplified.finance.core.ports.api.usecases;

import dev.da0hn.simplified.finance.core.usecases.InteractorUseCase;

import java.util.List;

@FunctionalInterface
public interface SearchCategoriesUseCase extends InteractorUseCase<SearchCategoriesUseCase.Input, SearchCategoriesUseCase.Output> {

  record Input(String queryId, String queryName, String queryDescription, String queryText) implements InteractorUseCase.Input { }

  record Output(List<OutputCategory> categories) implements InteractorUseCase.Output { }

  record OutputCategory(String categoryId, String categoryName, String categoryDescription) { }

}
