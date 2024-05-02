package dev.da0hn.simplified.finance.infrastructure.db.mappers;

import dev.da0hn.simplified.finance.core.domain.Category;
import dev.da0hn.simplified.finance.core.domain.validation.Validations;
import dev.da0hn.simplified.finance.core.domain.valueobjects.CategoryId;
import dev.da0hn.simplified.finance.core.shared.annotations.Mapper;
import dev.da0hn.simplified.finance.infrastructure.db.entities.CategoryEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

@Slf4j
@Mapper
@AllArgsConstructor
public class ToCategoryMapper implements Converter<CategoryEntity, Category> {

  @Override
  public Category convert(final CategoryEntity source) {
    if (log.isDebugEnabled()) {
      log.debug("m=convert(source={})", source);
    }
    Validations.requireNonNull(source, "source");

    return Category.builder()
      .categoryId(CategoryId.of(source.getId()))
      .name(source.getName())
      .description(source.getDescription())
      .build();
  }

}
