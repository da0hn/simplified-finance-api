package dev.da0hn.simplified.finance.core.domain.commands;

import dev.da0hn.simplified.finance.core.domain.Category;
import dev.da0hn.simplified.finance.core.domain.FutureExpenseEntry;
import dev.da0hn.simplified.finance.core.domain.enums.EntryStatus;
import dev.da0hn.simplified.finance.core.domain.valueobjects.Amount;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
public record NewCreditExpenseEntryCommand(
  FutureExpenseEntry futureExpenseEntry,
  String title,
  String description,
  Amount amount,
  EntryStatus status,
  LocalDateTime issuedAt,
  Long installmentNumber,
  Set<Category> categories
) {
}
