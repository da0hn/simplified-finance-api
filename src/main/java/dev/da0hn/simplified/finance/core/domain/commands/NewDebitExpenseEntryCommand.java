package dev.da0hn.simplified.finance.core.domain.commands;

import dev.da0hn.simplified.finance.core.domain.Category;
import dev.da0hn.simplified.finance.core.domain.enums.EntryStatus;
import dev.da0hn.simplified.finance.core.domain.valueobjects.Amount;
import dev.da0hn.simplified.finance.core.domain.valueobjects.IssuedAt;
import lombok.Builder;

import java.util.Set;

@Builder
public record NewDebitExpenseEntryCommand(
  String title,
  String description,
  Amount amount,
  EntryStatus status,
  IssuedAt issuedAt,
  Set<Category> categories
) {
}
