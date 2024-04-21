package dev.da0hn.simplified.finance.core.domain.commands;

import dev.da0hn.simplified.finance.core.domain.Amount;
import dev.da0hn.simplified.finance.core.domain.Category;
import dev.da0hn.simplified.finance.core.domain.EntryStatus;
import dev.da0hn.simplified.finance.core.domain.EntryType;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
public record NewDebitEntryCommand(
  String title,
  String description,
  Amount amount,
  EntryType type,
  EntryStatus status,
  LocalDateTime issuedAt,
  Set<Category> categories
) {
}
