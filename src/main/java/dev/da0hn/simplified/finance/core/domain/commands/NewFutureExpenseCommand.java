package dev.da0hn.simplified.finance.core.domain.commands;

import dev.da0hn.simplified.finance.core.domain.Category;
import dev.da0hn.simplified.finance.core.domain.valueobjects.Amount;
import dev.da0hn.simplified.finance.core.domain.valueobjects.InstallmentQuantity;

import java.time.LocalDateTime;
import java.util.Set;

public record NewFutureExpenseCommand(
  String title,
  String description,
  InstallmentQuantity installmentQuantity,
  Amount totalAmount,
  LocalDateTime issuedAt,
  Set<Category> categories
) {
}
