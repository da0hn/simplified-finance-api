package dev.da0hn.simplified.finance.infrastructure.db.mappers;

import dev.da0hn.simplified.finance.core.domain.Entry;
import dev.da0hn.simplified.finance.core.domain.enums.EntryStatus;
import dev.da0hn.simplified.finance.core.domain.enums.EntryType;
import dev.da0hn.simplified.finance.core.domain.enums.PaymentMethod;
import dev.da0hn.simplified.finance.core.domain.valueobjects.Amount;
import dev.da0hn.simplified.finance.core.domain.valueobjects.EntryId;
import dev.da0hn.simplified.finance.core.domain.valueobjects.IssuedAt;
import dev.da0hn.simplified.finance.core.shared.annotations.Mapper;
import dev.da0hn.simplified.finance.infrastructure.db.entities.EntryEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

import java.util.stream.Collectors;

@Slf4j
@Mapper
@AllArgsConstructor
public class ToEntryMapper implements Converter<EntryEntity, Entry> {

  private final ToCategoryMapper toCategoryMapper;

  @Override
  public Entry convert(final EntryEntity source) {
    if (log.isDebugEnabled()) {
      log.debug("m=convert(source={})", source);
    }

    return Entry.builder()
      .entryId(EntryId.of(source.getId()))
      .title(source.getTitle())
      .description(source.getDescription())
      .amount(Amount.of(source.getAmount()))
      .issuedAt(IssuedAt.of(source.getIssuedAt()))
      .type(EntryType.valueOf(source.getType().name()))
      .status(EntryStatus.valueOf(source.getStatus().name()))
      .paymentMethod(PaymentMethod.valueOf(source.getPaymentMethod().name()))
      .createdAt(source.getCreatedAt())
      .installmentDetail(null)
      .categories(
        source.getCategories().stream()
          .map(this.toCategoryMapper::convert)
          .collect(Collectors.toSet())
      )
      .build();
  }

}
