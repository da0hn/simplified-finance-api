package dev.da0hn.simplified.finance.core.domain.commands;

import dev.da0hn.simplified.finance.core.domain.Category;
import dev.da0hn.simplified.finance.core.domain.valueobjects.Amount;
import dev.da0hn.simplified.finance.core.domain.valueobjects.IssuedAt;

import java.util.Set;

public record NewOneTimePaymentCreditExpenseEntry(
  String title,
  String description,
  Amount amount,
  IssuedAt issuedAt,
  Set<Category> categories
) {
}
