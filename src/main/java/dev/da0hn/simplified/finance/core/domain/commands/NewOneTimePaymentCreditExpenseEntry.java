package dev.da0hn.simplified.finance.core.domain.commands;

import dev.da0hn.simplified.finance.core.domain.Category;
import dev.da0hn.simplified.finance.core.domain.valueobjects.Amount;

import java.time.LocalDateTime;
import java.util.Set;

public record NewOneTimePaymentCreditExpenseEntry(
  String title,
  String description,
  Amount amount,
  LocalDateTime issuedAt,
  Set<Category> categories
) {
}
