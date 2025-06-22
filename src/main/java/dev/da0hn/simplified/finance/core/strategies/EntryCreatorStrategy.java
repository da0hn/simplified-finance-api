package dev.da0hn.simplified.finance.core.strategies;

import dev.da0hn.simplified.finance.core.domain.Category;
import dev.da0hn.simplified.finance.core.domain.Entry;
import dev.da0hn.simplified.finance.core.domain.enums.EntryStatus;
import dev.da0hn.simplified.finance.core.domain.enums.EntryType;
import dev.da0hn.simplified.finance.core.domain.enums.PaymentMethod;
import dev.da0hn.simplified.finance.core.domain.valueobjects.Amount;
import dev.da0hn.simplified.finance.core.domain.valueobjects.InstallmentQuantity;
import dev.da0hn.simplified.finance.core.domain.valueobjects.IssuedAt;
import lombok.Builder;

import java.util.Set;

public interface EntryCreatorStrategy {

  Entry execute(Input input);

  Strategy type();

  enum Strategy {
    CREDIT_CARD_PAYMENT,
    CREDIT_CARD_ONE_TIME_PAYMENT,
    DEBIT_CARD_PAYMENT,
    CASH_PAYMENT
  }

  @Builder
  record Input(
    String title,
    String description,
    IssuedAt issuedAt,
    EntryType type,
    PaymentMethod paymentMethod,
    Amount amount,
    InstallmentQuantity installments,
    Amount partialAmount,
    EntryStatus status,
    Set<Category> categories
  ) { }

}
