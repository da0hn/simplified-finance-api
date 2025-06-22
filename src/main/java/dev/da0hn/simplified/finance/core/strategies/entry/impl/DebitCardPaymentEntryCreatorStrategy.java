package dev.da0hn.simplified.finance.core.strategies.entry.impl;

import dev.da0hn.simplified.finance.core.domain.Entry;
import dev.da0hn.simplified.finance.core.domain.commands.NewDebitExpenseEntryCommand;
import dev.da0hn.simplified.finance.core.ports.spi.repositories.EntryRepository;
import dev.da0hn.simplified.finance.core.shared.annotations.Strategy;
import dev.da0hn.simplified.finance.core.strategies.EntryCreatorStrategy;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Strategy
@AllArgsConstructor
public class DebitCardPaymentEntryCreatorStrategy implements EntryCreatorStrategy {

  private final EntryRepository entryRepository;

  @Override
  public Entry execute(final Input input) {
    log.info("method=execute(input={}, type={})", input, this.type());

    final var newEntry = Entry.debitExpenseEntry(
      new NewDebitExpenseEntryCommand(
        input.title(),
        input.description(),
        input.amount(),
        input.status(),
        input.issuedAt(),
        input.categories()
      )
    );

    this.entryRepository.creatEntry(newEntry);

    return newEntry;
  }

  @Override
  public Strategy type() {
    return Strategy.DEBIT_CARD_PAYMENT;
  }

}
