package dev.da0hn.simplified.finance.core.ports.api.usecases;

import dev.da0hn.simplified.finance.core.domain.enums.EntryStatus;
import dev.da0hn.simplified.finance.core.domain.enums.EntryType;
import dev.da0hn.simplified.finance.core.domain.enums.PaymentMethod;
import dev.da0hn.simplified.finance.core.usecases.InteractorUseCase;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@FunctionalInterface
public interface CreateEntryUseCase extends InteractorUseCase<CreateEntryUseCase.Input, CreateEntryUseCase.Output> {

  record Input(
    String title,
    String description,
    LocalDateTime issuedAt,
    EntryType type,
    PaymentMethod paymentMethod,
    BigDecimal amount,
    Long installments,
    BigDecimal partialAmount,
    EntryStatus status,
    Set<CategoryInput> categories
  ) implements InteractorUseCase.Input { }

  record CategoryInput(
    String id,
    String name
  ) { }

  record Output() implements InteractorUseCase.Output { }

}
