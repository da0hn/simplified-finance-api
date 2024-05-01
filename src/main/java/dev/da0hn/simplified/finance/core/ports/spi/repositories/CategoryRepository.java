package dev.da0hn.simplified.finance.core.ports.spi.repositories;

import dev.da0hn.simplified.finance.core.domain.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {

  void create(Category category);

  Optional<Category> findByName(String name);

  List<Category> searchBy(String queryName, String queryDescription, String queryText);

}
