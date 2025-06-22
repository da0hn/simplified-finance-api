package dev.da0hn.simplified.finance.infrastructure.db.mappers;

import dev.da0hn.simplified.finance.core.domain.Entry;
import dev.da0hn.simplified.finance.core.domain.InstallmentDetail;
import dev.da0hn.simplified.finance.core.domain.validation.Validations;
import dev.da0hn.simplified.finance.core.shared.annotations.Mapper;
import dev.da0hn.simplified.finance.infrastructure.db.entities.EntryEntity;
import dev.da0hn.simplified.finance.infrastructure.db.entities.EntryStatusEntityEnum;
import dev.da0hn.simplified.finance.infrastructure.db.entities.EntryTypeEntityEnum;
import dev.da0hn.simplified.finance.infrastructure.db.entities.PaymentMethodEntityEnum;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

import java.util.stream.Collectors;

@Slf4j
@Mapper
@AllArgsConstructor
public class ToEntryEntityMapper implements Converter<Entry, EntryEntity> {

  private final ToCategoryEntityMapper toCategoryEntityMapper;

  @Override
  public EntryEntity convert(final Entry source) {
    if (log.isDebugEnabled()) {
      log.debug("m=convert(source={})", source);
    }
    Validations.requireNonNull(source, "source");

    return EntryEntity.builder()
      .id(source.entryId().value())
      .title(source.title())
      .description(source.description())
      .amount(source.amount().value())
      .issuedAt(source.issuedAt().date())
      .type(EntryTypeEntityEnum.valueOf(source.type().name()))
      .status(EntryStatusEntityEnum.valueOf(source.status().name()))
      .paymentMethod(PaymentMethodEntityEnum.valueOf(source.paymentMethod().name()))
      .referenceMonth(source.issuedAt().referenceMonth())
      .createdAt(source.createdAt())
      .installmentNumber(source.installmentDetail().map(InstallmentDetail::installmentNumber).orElse(null))
      .categories(
        source.categories().stream()
          .map(this.toCategoryEntityMapper::convert)
          .collect(Collectors.toSet())
      )
      .build();
  }

}
