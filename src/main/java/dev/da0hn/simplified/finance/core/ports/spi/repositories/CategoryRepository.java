package dev.da0hn.simplified.finance.core.ports.spi.repositories;

import dev.da0hn.simplified.finance.core.domain.Category;
import dev.da0hn.simplified.finance.core.domain.valueobjects.CategoryId;
import lombok.Builder;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CategoryRepository {

  void create(Category category);

  void createInBatch(Collection<Category> categories);

  Optional<Category> findByName(String name);

  List<Category> searchBy(CategoryFilterCriteria criteria);

  Set<Category> findAllById(Collection<? extends CategoryId> categoriesId);

  @Builder
  record CategoryFilterCriteria(
    String queryId,
    String queryName,
    String queryDescription,
    String queryText
  ) { }

}
