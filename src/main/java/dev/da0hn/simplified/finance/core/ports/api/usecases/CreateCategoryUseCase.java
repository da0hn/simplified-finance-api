package dev.da0hn.simplified.finance.core.ports.api.usecases;

import dev.da0hn.simplified.finance.core.usecases.InteractorUseCase;
import lombok.Builder;

@FunctionalInterface
public interface CreateCategoryUseCase extends InteractorUseCase<CreateCategoryUseCase.Input, CreateCategoryUseCase.Output> {

  @Builder
  record Input(
    String categoryName,
    String categoryDescription
  ) implements InteractorUseCase.Input { }

  @Builder
  record Output(
    String categoryId
  ) implements InteractorUseCase.Output { }

}
