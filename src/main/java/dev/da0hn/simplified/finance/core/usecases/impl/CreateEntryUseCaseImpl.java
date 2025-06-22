package dev.da0hn.simplified.finance.core.usecases.impl;

import dev.da0hn.simplified.finance.core.domain.Category;
import dev.da0hn.simplified.finance.core.domain.Entry;
import dev.da0hn.simplified.finance.core.domain.valueobjects.Amount;
import dev.da0hn.simplified.finance.core.domain.valueobjects.CategoryId;
import dev.da0hn.simplified.finance.core.domain.valueobjects.InstallmentQuantity;
import dev.da0hn.simplified.finance.core.domain.valueobjects.IssuedAt;
import dev.da0hn.simplified.finance.core.ports.api.usecases.CreateEntryUseCase;
import dev.da0hn.simplified.finance.core.ports.spi.repositories.CategoryRepository;
import dev.da0hn.simplified.finance.core.shared.annotations.UseCase;
import dev.da0hn.simplified.finance.core.strategies.EntryCreatorStrategy;
import lombok.AllArgsConstructor;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@UseCase
@AllArgsConstructor
public class CreateEntryUseCaseImpl implements CreateEntryUseCase {

  private final Set<EntryCreatorStrategy> strategies;

  private final CategoryRepository categoryRepository;

  @Override
  public Output execute(final Input input) {

    final var categories = this.getOrCreateCategories(input);

    final var strategyType = this.selectStrategy(input);

    final var newEntry = this.strategies.stream()
      .filter(strategy -> strategy.type().equals(strategyType))
      .findFirst()
      .map(strategy -> strategy.execute(
        EntryCreatorStrategy.Input.builder()
          .title(input.title())
          .description(input.description())
          .amount(Amount.of(input.amount()))
          .issuedAt(IssuedAt.of(input.issuedAt()))
          .paymentMethod(input.paymentMethod())
          .installments(InstallmentQuantity.of(input.installments()))
          .partialAmount(Amount.of(input.partialAmount()))
          .status(input.status())
          .categories(categories)
          .build()
      )).orElseThrow(() -> new IllegalArgumentException("Strategy implementation not found"));

    return new Output();
  }

  private EntryCreatorStrategy.Strategy selectStrategy(final Input input) {
    return switch (input.paymentMethod()) {
      case CASH -> EntryCreatorStrategy.Strategy.CASH_PAYMENT;
      case DEBIT_CARD -> EntryCreatorStrategy.Strategy.DEBIT_CARD_PAYMENT;
      case CREDIT_CARD -> {
        if (input.installments() != null && input.installments() == 1)
          yield EntryCreatorStrategy.Strategy.CREDIT_CARD_ONE_TIME_PAYMENT;
        yield EntryCreatorStrategy.Strategy.CREDIT_CARD_PAYMENT;
      }
    };
  }

  private Set<Category> getOrCreateCategories(final Input input) {
    final var categoriesMap = input.categories().stream()
      .collect(Collectors.partitioningBy(category -> Objects.isNull(category.id())));

    final var newCategories = categoriesMap.get(false).stream()
      .map(newCategory -> Category.newCategory(newCategory.name(), null))
      .toList();

    this.categoryRepository.createInBatch(newCategories);

    final var categoriesId = categoriesMap.get(true).stream()
      .map(CategoryInput::id)
      .map(CategoryId::of)
      .toList();

    final var existentCategories = this.categoryRepository.findAllById(categoriesId);

    return Stream.concat(
      existentCategories.stream(),
      newCategories.stream()
    ).collect(Collectors.toSet());
  }

}
