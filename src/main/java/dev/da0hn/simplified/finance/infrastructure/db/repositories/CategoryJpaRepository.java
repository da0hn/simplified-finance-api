package dev.da0hn.simplified.finance.infrastructure.db.repositories;

import dev.da0hn.simplified.finance.infrastructure.db.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryJpaRepository extends JpaRepository<CategoryEntity, String> {

  @Query(
    """
    select category
    from Category category
    where
      (:id is null or lower(category.id) like lower(concat('%', :id, '%')) ) and
      (:name is null or lower(category.name) like lower(concat('%', :name, '%'))) and
      (:description is null or lower(category.description) like lower(concat('%', :description, '%')))
    and (
      (:text is null or :text = '' ) or
      lower(category.name) like lower(concat('%', :text, '%')) or
      lower(category.description) like lower(concat('%', :text, '%'))
    )
    """
  )
  List<CategoryEntity> findAllBy(String id, String name, String description, String text);

  Optional<CategoryEntity> findByName(String name);

}
