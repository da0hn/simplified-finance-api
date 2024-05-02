package dev.da0hn.simplified.finance.infrastructure.db.repositories.adapters;

import dev.da0hn.simplified.finance.core.domain.Category;
import dev.da0hn.simplified.finance.core.domain.validation.Validations;
import dev.da0hn.simplified.finance.core.ports.spi.repositories.CategoryRepository;
import dev.da0hn.simplified.finance.core.shared.annotations.Adapter;
import dev.da0hn.simplified.finance.infrastructure.db.entities.CategoryEntity;
import dev.da0hn.simplified.finance.infrastructure.db.mappers.RepositoryDataMapper;
import dev.da0hn.simplified.finance.infrastructure.db.repositories.CategoryJpaRepository;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@Adapter
@AllArgsConstructor
public class CategoryRepositoryAdapter implements CategoryRepository {

  private final CategoryJpaRepository categoryJpaRepository;

  private final RepositoryDataMapper repositoryDataMapper;

  @Override
  public void create(final Category category) {
    final var categoryEntity = this.repositoryDataMapper.toEntity(category, CategoryEntity.class);
    Validations.requireNonNull(categoryEntity, "categoryEntity");
    this.categoryJpaRepository.save(categoryEntity);
  }

  @Override
  public Optional<Category> findByName(final String name) {
    return this.categoryJpaRepository.findByName(name)
      .map(category -> this.repositoryDataMapper.toDomain(category, Category.class));
  }

  @Override
  public List<Category> searchBy(final CategoryFilterCriteria criteria) {
    final var categories = this.categoryJpaRepository.findAllBy(
      criteria.queryId(),
      criteria.queryName(),
      criteria.queryDescription(),
      criteria.queryText()
    );
    return categories.stream()
      .map(category -> this.repositoryDataMapper.toDomain(category, Category.class))
      .toList();
  }

}
