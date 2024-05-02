package dev.da0hn.simplified.finance.infrastructure.db.mappers;

import dev.da0hn.simplified.finance.core.domain.exceptions.ExceptionFactory;
import dev.da0hn.simplified.finance.core.shared.annotations.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;

@Mapper
@AllArgsConstructor
public class RepositoryDataMapperImpl implements RepositoryDataMapper {

  private final ConversionService conversionService;

  public <E, D> E toEntity(final D source, final Class<? extends E> entityClass) {
    if (!this.conversionService.canConvert(source.getClass(), entityClass)) {
      throw ExceptionFactory.illegalArgument(
        "Não é possível converter o domínio %s para a entidade %s",
        source.getClass().getSimpleName(),
        entityClass.getSimpleName()
      );
    }
    return this.conversionService.convert(source, entityClass);
  }

  public <E, D> D toDomain(final E source, final Class<? extends D> domainClass) {
    if (!this.conversionService.canConvert(source.getClass(), domainClass)) {
      throw ExceptionFactory.illegalArgument(
        "Não é possível converter a entidade %s para o domínio %s",
        source.getClass().getSimpleName(),
        domainClass.getSimpleName()
      );
    }
    return this.conversionService.convert(source, domainClass);
  }

}
