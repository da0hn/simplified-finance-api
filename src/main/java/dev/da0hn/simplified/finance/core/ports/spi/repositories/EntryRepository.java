package dev.da0hn.simplified.finance.core.ports.spi.repositories;

import dev.da0hn.simplified.finance.core.domain.Entry;
import dev.da0hn.simplified.finance.core.domain.FutureExpenseEntry;

public interface EntryRepository {

  void creatEntry(Entry newEntry);

  void createFutureExpenseEntry(FutureExpenseEntry newFutureExpenseEntry);

}
