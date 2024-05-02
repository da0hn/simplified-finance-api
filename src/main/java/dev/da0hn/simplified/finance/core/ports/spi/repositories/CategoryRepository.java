package dev.da0hn.simplified.finance.core.ports.spi.repositories;

import dev.da0hn.simplified.finance.core.domain.Category;
import lombok.Builder;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {

  void create(Category category);

  Optional<Category> findByName(String name);

  List<Category> searchBy(CategoryFilterCriteria criteria);

  @Builder
  record CategoryFilterCriteria(
    String queryId,
    String queryName,
    String queryDescription,
    String queryText
  ) { }

}
