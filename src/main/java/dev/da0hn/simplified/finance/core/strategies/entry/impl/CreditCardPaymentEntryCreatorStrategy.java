package dev.da0hn.simplified.finance.core.strategies.entry.impl;

import dev.da0hn.simplified.finance.core.domain.Entry;
import dev.da0hn.simplified.finance.core.domain.FutureExpenseEntry;
import dev.da0hn.simplified.finance.core.domain.commands.NewFutureExpenseCommand;
import dev.da0hn.simplified.finance.core.ports.spi.repositories.EntryRepository;
import dev.da0hn.simplified.finance.core.shared.annotations.Strategy;
import dev.da0hn.simplified.finance.core.strategies.EntryCreatorStrategy;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Strategy
@AllArgsConstructor
public class CreditCardPaymentEntryCreatorStrategy implements EntryCreatorStrategy {

  private final EntryRepository entryRepository;

  @Override
  public Entry execute(final Input input) {
    log.info("method=execute(input={}, type={})", input, this.type());

    final var installments = input.installments();
    final var futureExpenseEntry = FutureExpenseEntry.newFutureExpense(
      new NewFutureExpenseCommand(
        input.title(),
        input.description(),
        installments,
        input.amount(),
        input.issuedAt(),
        input.categories()
      )
    );

    this.entryRepository.createFutureExpenseEntry(futureExpenseEntry);

    return futureExpenseEntry.getFirstInstallmentEntry();
  }

  @Override
  public EntryCreatorStrategy.Strategy type() {
    return Strategy.CREDIT_CARD_PAYMENT;
  }

}
