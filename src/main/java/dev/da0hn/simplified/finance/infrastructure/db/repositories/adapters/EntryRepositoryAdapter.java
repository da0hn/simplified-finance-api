package dev.da0hn.simplified.finance.infrastructure.db.repositories.adapters;

import dev.da0hn.simplified.finance.core.domain.Entry;
import dev.da0hn.simplified.finance.core.domain.FutureExpenseEntry;
import dev.da0hn.simplified.finance.core.ports.spi.repositories.EntryRepository;
import dev.da0hn.simplified.finance.core.shared.annotations.Adapter;
import dev.da0hn.simplified.finance.infrastructure.db.repositories.EntryJpaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Adapter
@AllArgsConstructor
public class EntryRepositoryAdapter implements EntryRepository {

  private final EntryJpaRepository entryJpaRepository;

  @Override
  public void creatEntry(final Entry newEntry) {
    log.info("method=creatEntry(newEntry={})", newEntry);

    this.entryJpaRepository.save(null);
  }

  @Override
  public void createFutureExpenseEntry(final FutureExpenseEntry newFutureExpenseEntry) {
    log.info("method=createFutureExpenseEntry(newFutureExpenseEntry={})", newFutureExpenseEntry);

  }

}
