package dev.da0hn.simplified.finance.infrastructure.db.repositories;

import dev.da0hn.simplified.finance.infrastructure.db.entities.FutureExpenseEntryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FutureExpenseEntryJpaRepository extends JpaRepository<FutureExpenseEntryEntity, String> {
}
