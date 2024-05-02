package dev.da0hn.simplified.finance.infrastructure.db.mappers;

import dev.da0hn.simplified.finance.core.domain.Category;
import dev.da0hn.simplified.finance.core.domain.validation.Validations;
import dev.da0hn.simplified.finance.core.shared.annotations.Mapper;
import dev.da0hn.simplified.finance.infrastructure.db.entities.CategoryEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

@Slf4j
@Mapper
@AllArgsConstructor
public class ToCategoryEntityMapper implements Converter<Category, CategoryEntity> {

  @Override
  public CategoryEntity convert(final Category source) {
    if (log.isDebugEnabled()) {
      log.debug("m=convert(source={})", source);
    }
    Validations.requireNonNull(source, "source");

    return CategoryEntity.builder()
      .id(source.categoryId().value())
      .name(source.name())
      .description(source.description())
      .build();
  }

}
