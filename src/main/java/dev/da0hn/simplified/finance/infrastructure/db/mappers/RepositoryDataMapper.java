package dev.da0hn.simplified.finance.infrastructure.db.mappers;

public interface RepositoryDataMapper {

  <E, D> E toEntity(final D source, final Class<? extends E> entityClass);

  <E, D> D toDomain(final E source, final Class<? extends D> domainClass);

}
