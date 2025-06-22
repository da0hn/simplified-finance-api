package dev.da0hn.simplified.finance.core.strategies.entry.impl;

import dev.da0hn.simplified.finance.core.domain.Entry;
import dev.da0hn.simplified.finance.core.shared.annotations.Strategy;
import dev.da0hn.simplified.finance.core.strategies.EntryCreatorStrategy;
import lombok.AllArgsConstructor;

@Strategy
@AllArgsConstructor
public class CashPaymentEntryCreatorStrategy implements EntryCreatorStrategy {

  @Override
  public Entry execute(final Input input) {
    throw new UnsupportedOperationException("Cash payment is not supported yet");
  }

  @Override
  public Strategy type() {
    return Strategy.CASH_PAYMENT;
  }

}
