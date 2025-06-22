package dev.da0hn.simplified.finance.infrastructure.db.repositories;

import dev.da0hn.simplified.finance.infrastructure.db.entities.EntryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntryJpaRepository extends JpaRepository<EntryEntity, String> {
}
