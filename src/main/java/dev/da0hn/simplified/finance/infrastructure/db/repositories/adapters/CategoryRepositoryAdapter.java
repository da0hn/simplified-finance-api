package dev.da0hn.simplified.finance.infrastructure.db.repositories.adapters;

import dev.da0hn.simplified.finance.core.domain.Category;
import dev.da0hn.simplified.finance.core.domain.validation.Validations;
import dev.da0hn.simplified.finance.core.domain.valueobjects.CategoryId;
import dev.da0hn.simplified.finance.core.ports.spi.repositories.CategoryRepository;
import dev.da0hn.simplified.finance.core.shared.annotations.Adapter;
import dev.da0hn.simplified.finance.infrastructure.db.entities.CategoryEntity;
import dev.da0hn.simplified.finance.infrastructure.db.mappers.RepositoryDataMapper;
import dev.da0hn.simplified.finance.infrastructure.db.repositories.CategoryJpaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Adapter
@AllArgsConstructor
public class CategoryRepositoryAdapter implements CategoryRepository {

  private final CategoryJpaRepository categoryJpaRepository;

  private final RepositoryDataMapper repositoryDataMapper;

  @Override
  public void create(final Category category) {
    log.info("method=create(category={})", category);

    final var categoryEntity = this.repositoryDataMapper.toEntity(category, CategoryEntity.class);
    Validations.requireNonNull(categoryEntity, "categoryEntity");
    this.categoryJpaRepository.save(categoryEntity);
  }

  @Override
  public void createInBatch(final Collection<Category> categories) {
    log.info("method=createInBatch(categories={})", categories);

    final var categoryEntities = categories.stream()
      .map(category -> this.repositoryDataMapper.toEntity(category, CategoryEntity.class))
      .toList();

    this.categoryJpaRepository.saveAll(categoryEntities);
  }

  @Override
  public Optional<Category> findByName(final String name) {
    log.info("method=findByName(name={})", name);
    return this.categoryJpaRepository.findByName(name)
      .map(category -> this.repositoryDataMapper.toDomain(category, Category.class));
  }

  @Override
  public List<Category> searchBy(final CategoryFilterCriteria criteria) {
    log.info("method=searchBy(criteria={})", criteria);

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

  @Override
  public Set<Category> findAllById(final Collection<? extends CategoryId> categoriesId) {
    log.info("method=findAllById(categoriesId={})", categoriesId);

    final var ids = categoriesId.stream().map(CategoryId::value).toList();

    return this.categoryJpaRepository.findAllById(ids).stream()
      .map(category -> this.repositoryDataMapper.toDomain(category, Category.class))
      .collect(Collectors.toSet());
  }

}
