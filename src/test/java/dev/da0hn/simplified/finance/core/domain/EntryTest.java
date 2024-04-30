package dev.da0hn.simplified.finance.core.domain;

import dev.da0hn.simplified.finance.core.domain.commands.NewDebitExpenseEntryCommand;
import dev.da0hn.simplified.finance.core.domain.commands.NewFutureExpenseCommand;
import dev.da0hn.simplified.finance.core.domain.commands.NewOneTimePaymentCreditExpenseEntry;
import dev.da0hn.simplified.finance.core.domain.enums.EntryStatus;
import dev.da0hn.simplified.finance.core.domain.enums.EntryType;
import dev.da0hn.simplified.finance.core.domain.enums.PaymentMethod;
import dev.da0hn.simplified.finance.core.domain.exceptions.DomainConstraintViolationException;
import dev.da0hn.simplified.finance.core.domain.exceptions.DomainValidationException;
import dev.da0hn.simplified.finance.core.domain.validation.DomainValidationMessages;
import dev.da0hn.simplified.finance.core.domain.valueobjects.Amount;
import dev.da0hn.simplified.finance.core.domain.valueobjects.InstallmentQuantity;
import dev.da0hn.simplified.finance.core.domain.valueobjects.IssuedAt;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Set;

@DisplayName("Entry Domain tests")
class EntryTest {

  @Nested
  @DisplayName("Debit Expense Entry tests")
  class DebitExpenseEntryTests {

    @Test
    @DisplayName("Should create a new Debit entry")
    void test1() {
      final var expectedIssuedAt = IssuedAt.of(LocalDateTime.now().minusDays(10));
      final var expectedAmount = Amount.of(10.0);
      final var expectedStatus = EntryStatus.PENDING;
      final var expectedTitle = "title";
      final var expectedDescription = "description";

      final var command = new NewDebitExpenseEntryCommand(
        expectedTitle,
        expectedDescription,
        expectedAmount,
        expectedStatus,
        expectedIssuedAt,
        Set.of(Category.newCategory("category", "category description"))
      );

      final var debitEntry = Entry.debitExpenseEntry(command);

      Assertions.assertThat(debitEntry).isNotNull();
      Assertions.assertThat(debitEntry.entryId()).isNotNull();
      Assertions.assertThat(debitEntry.title()).isEqualTo(expectedTitle);
      Assertions.assertThat(debitEntry.description()).isEqualTo(expectedDescription);
      Assertions.assertThat(debitEntry.type()).isEqualTo(EntryType.EXPENSE);
      Assertions.assertThat(debitEntry.status()).isEqualTo(expectedStatus);
      Assertions.assertThat(debitEntry.amount()).isEqualTo(expectedAmount);
      Assertions.assertThat(debitEntry.paymentMethod()).isEqualTo(PaymentMethod.DEBIT_CARD);
      Assertions.assertThat(debitEntry.createdAt()).isNotNull();
      Assertions.assertThat(debitEntry.issuedAt()).isEqualTo(expectedIssuedAt);
      Assertions.assertThat(debitEntry.installmentDetail()).isEmpty();
      Assertions.assertThat(debitEntry.categories()).hasSize(1);
    }

    @Test
    @DisplayName("Should not create an Debit Entry without title")
    void test2() {
      final var expectedIssuedAt = IssuedAt.of(LocalDateTime.now().minusDays(10));
      final var expectedAmount = Amount.of(10.0);
      final var expectedStatus = EntryStatus.PENDING;
      final var expectedDescription = "description";

      final var command = new NewDebitExpenseEntryCommand(
        null,
        expectedDescription,
        expectedAmount,
        expectedStatus,
        expectedIssuedAt,
        Set.of(Category.newCategory("category", "category description"))
      );

      Assertions.assertThatThrownBy(() -> Entry.debitExpenseEntry(command))
        .isInstanceOf(DomainConstraintViolationException.class)
        .hasMessageContaining(DomainValidationMessages.ENTRY_TITLE_NOT_BLANK);
    }

    @Test
    @DisplayName("Should not create an Debit Entry without description")
    void test3() {
      final var expectedIssuedAt = IssuedAt.of(LocalDateTime.now().minusDays(10));
      final var expectedAmount = Amount.of(10.0);
      final var expectedStatus = EntryStatus.PENDING;
      final var expectedTitle = "title";

      final var command = new NewDebitExpenseEntryCommand(
        expectedTitle,
        null,
        expectedAmount,
        expectedStatus,
        expectedIssuedAt,
        Set.of(Category.newCategory("category", "category description"))
      );

      Assertions.assertThatThrownBy(() -> Entry.debitExpenseEntry(command))
        .isInstanceOf(DomainConstraintViolationException.class)
        .hasMessageContaining(DomainValidationMessages.ENTRY_DESCRIPTION_NOT_BLANK);
    }

    @Test
    @DisplayName("Should not create an Debit Entry without amount")
    void test4() {
      final var expectedIssuedAt = IssuedAt.of(LocalDateTime.now().minusDays(10));
      final var expectedStatus = EntryStatus.PENDING;
      final var expectedTitle = "title";
      final var expectedDescription = "description";

      final var command = new NewDebitExpenseEntryCommand(
        expectedTitle,
        expectedDescription,
        null,
        expectedStatus,
        expectedIssuedAt,
        Set.of(Category.newCategory("category", "category description"))
      );

      Assertions.assertThatThrownBy(() -> Entry.debitExpenseEntry(command))
        .isInstanceOf(DomainConstraintViolationException.class)
        .hasMessageContaining(DomainValidationMessages.ENTRY_AMOUNT_NOT_NULL);
    }

    @Test
    @DisplayName("Should not create an Debit Entry without status")
    void test5() {
      final var expectedIssuedAt = IssuedAt.of(LocalDateTime.now().minusDays(10));
      final var expectedAmount = Amount.of(10.0);
      final var expectedTitle = "title";
      final var expectedDescription = "description";

      final var command = new NewDebitExpenseEntryCommand(
        expectedTitle,
        expectedDescription,
        expectedAmount,
        null,
        expectedIssuedAt,
        Set.of(Category.newCategory("category", "category description"))
      );

      Assertions.assertThatThrownBy(() -> Entry.debitExpenseEntry(command))
        .isInstanceOf(DomainConstraintViolationException.class)
        .hasMessageContaining(DomainValidationMessages.ENTRY_STATUS_NOT_NULL);
    }

    @Test
    @DisplayName("Should not create an Debit Entry without issuedAt")
    void test6() {
      final var expectedAmount = Amount.of(10.0);
      final var expectedStatus = EntryStatus.PENDING;
      final var expectedTitle = "title";
      final var expectedDescription = "description";

      final var command = new NewDebitExpenseEntryCommand(
        expectedTitle,
        expectedDescription,
        expectedAmount,
        expectedStatus,
        null,
        Set.of(Category.newCategory("category", "category description"))
      );

      Assertions.assertThatThrownBy(() -> Entry.debitExpenseEntry(command))
        .isInstanceOf(DomainConstraintViolationException.class)
        .hasMessageContaining(DomainValidationMessages.ENTRY_ISSUED_AT_NOT_NULL);
    }

    @Test
    @DisplayName("Should not create an Debit Entry with categories null")
    void test7() {
      final var expectedIssuedAt = IssuedAt.of(LocalDateTime.now().minusDays(10));
      final var expectedAmount = Amount.of(10.0);
      final var expectedStatus = EntryStatus.PENDING;
      final var expectedTitle = "title";
      final var expectedDescription = "description";

      final var command = new NewDebitExpenseEntryCommand(
        expectedTitle,
        expectedDescription,
        expectedAmount,
        expectedStatus,
        expectedIssuedAt,
        null
      );

      Assertions.assertThatThrownBy(() -> Entry.debitExpenseEntry(command))
        .isInstanceOf(DomainConstraintViolationException.class)
        .hasMessageContaining(DomainValidationMessages.ENTRY_CATEGORIES_NOT_EMPTY);
    }

    @Test
    @DisplayName("Should not create an Debit Entry with categories empty")
    void test8() {
      final var expectedIssuedAt = IssuedAt.of(LocalDateTime.now().minusDays(10));
      final var expectedAmount = Amount.of(10.0);
      final var expectedStatus = EntryStatus.PENDING;
      final var expectedTitle = "title";
      final var expectedDescription = "description";

      final var command = new NewDebitExpenseEntryCommand(
        expectedTitle,
        expectedDescription,
        expectedAmount,
        expectedStatus,
        expectedIssuedAt,
        Set.of()
      );

      Assertions.assertThatThrownBy(() -> Entry.debitExpenseEntry(command))
        .isInstanceOf(DomainConstraintViolationException.class)
        .hasMessageContaining(DomainValidationMessages.ENTRY_CATEGORIES_NOT_EMPTY);
    }

    @Test
    @DisplayName("Should not create an Debit Entry with null command")
    void test9() {
      Assertions.assertThatThrownBy(() -> Entry.debitExpenseEntry(null))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("NewDebitEntryCommand must not be null");
    }

  }

  @Nested
  @DisplayName("Credit Expense Entry tests")
  class CreditExpenseEntryTests {

    @Test
    @DisplayName("Should create a new Credit entry with Installments")
    void test1() {
      final var expectedIssuedAt = IssuedAt.of(LocalDateTime.now().minusDays(10));
      final var expectedInstallmentQuantity = InstallmentQuantity.of(5L);
      final var expectedTotalAmount = Amount.of(100);
      final var expectedPartialAmount = Amount.of(20);
      final var expectedTitle = "title";
      final var expectedDescription = "description";

      final var futureExpenseEntry = FutureExpenseEntry.newFutureExpense(
        new NewFutureExpenseCommand(
          expectedTitle,
          expectedDescription,
          expectedInstallmentQuantity,
          expectedTotalAmount,
          expectedIssuedAt,
          Set.of(Category.newCategory("category", "category description"))
        )
      );
      Assertions.assertThat(futureExpenseEntry.futureExpenseEntryId()).isNotNull();
      Assertions.assertThat(futureExpenseEntry.installments()).isEqualTo(expectedInstallmentQuantity);
      Assertions.assertThat(futureExpenseEntry.totalAmount()).isEqualTo(expectedTotalAmount);
      Assertions.assertThat(futureExpenseEntry.partialAmount()).isEqualTo(expectedPartialAmount);
      Assertions.assertThat(futureExpenseEntry.issuedAt()).isEqualTo(expectedIssuedAt);
      Assertions.assertThat(futureExpenseEntry.createdAt()).isNotNull();
      Assertions.assertThat(futureExpenseEntry.entries()).hasSize(5);
      futureExpenseEntry.entries().forEach(entry -> {
        Assertions.assertThat(entry.installmentDetail()).isPresent();
        final var installmentNumber = entry.installmentDetail().get().installmentNumber();
        Assertions.assertThat(installmentNumber).isNotNull();
        Assertions.assertThat(entry.installmentDetail().get().futureExpenseEntry()).isEqualTo(futureExpenseEntry);
        Assertions.assertThat(entry.installmentDetail().get().entryId()).isEqualTo(entry.entryId());
        Assertions.assertThat(entry.entryId()).isNotNull();
        Assertions.assertThat(entry.title()).isEqualTo(expectedTitle);
        Assertions.assertThat(entry.description()).isEqualTo(expectedDescription);
        Assertions.assertThat(entry.type()).isEqualTo(EntryType.EXPENSE);
        Assertions.assertThat(entry.status()).isEqualTo(installmentNumber == 1 ? EntryStatus.PAID : EntryStatus.PENDING);
        Assertions.assertThat(entry.amount()).isEqualTo(expectedPartialAmount);
        Assertions.assertThat(entry.paymentMethod()).isEqualTo(PaymentMethod.CREDIT_CARD);
        Assertions.assertThat(entry.createdAt()).isNotNull();
        final var expectedEntryIssuedAt = expectedIssuedAt.plusMonths(installmentNumber == 1 ? 0 : installmentNumber);
        Assertions.assertThat(entry.issuedAt()).isEqualTo(expectedEntryIssuedAt);
        Assertions.assertThat(entry.categories()).hasSize(1);
      });
    }

    @Test
    @DisplayName("Should not create an Credit Entry with title null")
    void test2() {
      final var expectedIssuedAt = IssuedAt.of(LocalDateTime.now().minusDays(10));
      final var command = new NewFutureExpenseCommand(
        null,
        "description",
        InstallmentQuantity.of(5L),
        Amount.of(100),
        expectedIssuedAt,
        Set.of(Category.newCategory("category", "category description"))
      );
      Assertions.assertThatThrownBy(() -> FutureExpenseEntry.newFutureExpense(command))
        .isInstanceOf(DomainConstraintViolationException.class)
        .hasMessageContaining(DomainValidationMessages.ENTRY_TITLE_NOT_BLANK);
    }

    @Test
    @DisplayName("Should not create an Credit Entry with description null")
    void test3() {
      final var expectedIssuedAt = IssuedAt.of(LocalDateTime.now().minusDays(10));
      final var command = new NewFutureExpenseCommand(
        "title",
        null,
        InstallmentQuantity.of(5L),
        Amount.of(100),
        expectedIssuedAt,
        Set.of(Category.newCategory("category", "category description"))
      );
      Assertions.assertThatThrownBy(() -> FutureExpenseEntry.newFutureExpense(command))
        .isInstanceOf(DomainConstraintViolationException.class)
        .hasMessageContaining(DomainValidationMessages.ENTRY_DESCRIPTION_NOT_BLANK);
    }

    @Test
    @DisplayName("Should not create an Credit Entry with installmentQuantity null")
    void test4() {
      final var expectedIssuedAt = IssuedAt.of(LocalDateTime.now().minusDays(10));
      final var command = new NewFutureExpenseCommand(
        "title",
        "description",
        null,
        Amount.of(100),
        expectedIssuedAt,
        Set.of(Category.newCategory("category", "category description"))
      );
      Assertions.assertThatThrownBy(() -> FutureExpenseEntry.newFutureExpense(command))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("InstallmentQuantity must not be null");
    }

    @Test
    @DisplayName("Should not create an Credit Entry with totalAmount null")
    void test7() {
      final var expectedIssuedAt = IssuedAt.of(LocalDateTime.now().minusDays(10));
      final var command = new NewFutureExpenseCommand(
        "title",
        "description",
        InstallmentQuantity.of(5L),
        null,
        expectedIssuedAt,
        Set.of(Category.newCategory("category", "category description"))
      );
      Assertions.assertThatThrownBy(() -> FutureExpenseEntry.newFutureExpense(command))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("TotalAmount must not be null");
    }

    @Test
    @DisplayName("Should not create an Credit Entry with issuedAt null")
    void test8() {
      final var command = new NewFutureExpenseCommand(
        "title",
        "description",
        InstallmentQuantity.of(5L),
        Amount.of(100),
        null,
        Set.of(Category.newCategory("category", "category description"))
      );
      Assertions.assertThatThrownBy(() -> FutureExpenseEntry.newFutureExpense(command))
        .isInstanceOf(DomainConstraintViolationException.class)
        .hasMessageContaining(DomainValidationMessages.FUTURE_EXPENSE_ENTRY_ISSUED_AT_NOT_NULL);
    }

    @Test
    @DisplayName("Should not create an Credit Entry with categories null")
    void test9() {
      final var expectedIssuedAt = IssuedAt.of(LocalDateTime.now().minusDays(10));
      final var command = new NewFutureExpenseCommand(
        "title",
        "description",
        InstallmentQuantity.of(5L),
        Amount.of(100),
        expectedIssuedAt,
        null
      );
      Assertions.assertThatThrownBy(() -> FutureExpenseEntry.newFutureExpense(command))
        .isInstanceOf(DomainValidationException.class)
        .hasMessageContaining(DomainValidationMessages.FUTURE_EXPENSE_ENTRY_CATEGORIES_NOT_EMPTY);
    }

    @Test
    @DisplayName("Should not create an Credit Entry with categories empty")
    void test10() {
      final var expectedIssuedAt = IssuedAt.of(LocalDateTime.now().minusDays(10));
      final var command = new NewFutureExpenseCommand(
        "title",
        "description",
        InstallmentQuantity.of(5L),
        Amount.of(100),
        expectedIssuedAt,
        Set.of()
      );
      Assertions.assertThatThrownBy(() -> FutureExpenseEntry.newFutureExpense(command))
        .isInstanceOf(DomainValidationException.class)
        .hasMessageContaining(DomainValidationMessages.FUTURE_EXPENSE_ENTRY_CATEGORIES_NOT_EMPTY);
    }

  }

  @Nested
  @DisplayName("One Time Payment Credit Expense Entry tests")
  class OneTimePaymentCreditExpenseEntry {

    @Test
    @DisplayName("Should create a new One Time Payment Credit entry")
    void test1() {
      final var expectedIssuedAt = IssuedAt.of(LocalDateTime.now().minusDays(10));
      final var expectedAmount = Amount.of(100);
      final var expectedStatus = EntryStatus.PAID;
      final var expectedTitle = "title";
      final var expectedDescription = "description";

      final var command = new NewOneTimePaymentCreditExpenseEntry(
        expectedTitle,
        expectedDescription,
        expectedAmount,
        expectedIssuedAt,
        Set.of(Category.newCategory("category", "category description"))
      );

      final var oneTimePaymentCreditExpenseEntry = Entry.oneTimePaymentCreditExpenseEntry(command);

      Assertions.assertThat(oneTimePaymentCreditExpenseEntry).isNotNull();
      Assertions.assertThat(oneTimePaymentCreditExpenseEntry.entryId()).isNotNull();
      Assertions.assertThat(oneTimePaymentCreditExpenseEntry.title()).isEqualTo(expectedTitle);
      Assertions.assertThat(oneTimePaymentCreditExpenseEntry.description()).isEqualTo(expectedDescription);
      Assertions.assertThat(oneTimePaymentCreditExpenseEntry.type()).isEqualTo(EntryType.EXPENSE);
      Assertions.assertThat(oneTimePaymentCreditExpenseEntry.status()).isEqualTo(expectedStatus);
      Assertions.assertThat(oneTimePaymentCreditExpenseEntry.amount()).isEqualTo(expectedAmount);
      Assertions.assertThat(oneTimePaymentCreditExpenseEntry.paymentMethod()).isEqualTo(PaymentMethod.CREDIT_CARD);
      Assertions.assertThat(oneTimePaymentCreditExpenseEntry.createdAt()).isNotNull();
      Assertions.assertThat(oneTimePaymentCreditExpenseEntry.issuedAt()).isEqualTo(expectedIssuedAt);
      Assertions.assertThat(oneTimePaymentCreditExpenseEntry.installmentDetail()).isEmpty();
      Assertions.assertThat(oneTimePaymentCreditExpenseEntry.categories()).hasSize(1);
    }

    @Test
    @DisplayName("Should not create an One Time Payment Credit Entry without title")
    void test2() {
      final var expectedIssuedAt = IssuedAt.of(LocalDateTime.now().minusDays(10));
      final var expectedAmount = Amount.of(100);
      final var expectedDescription = "description";

      final var command = new NewOneTimePaymentCreditExpenseEntry(
        null,
        expectedDescription,
        expectedAmount,
        expectedIssuedAt,
        Set.of(Category.newCategory("category", "category description"))
      );

      Assertions.assertThatThrownBy(() -> Entry.oneTimePaymentCreditExpenseEntry(command))
        .isInstanceOf(DomainConstraintViolationException.class)
        .hasMessageContaining(DomainValidationMessages.ENTRY_TITLE_NOT_BLANK);
    }

    @Test
    @DisplayName("Should not create an One Time Payment Credit Entry without description")
    void test3() {
      final var expectedIssuedAt = IssuedAt.of(LocalDateTime.now().minusDays(10));
      final var expectedAmount = Amount.of(100);
      final var expectedStatus = EntryStatus.PENDING;
      final var expectedTitle = "title";

      final var command = new NewOneTimePaymentCreditExpenseEntry(
        expectedTitle,
        null,
        expectedAmount,
        expectedIssuedAt,
        Set.of(Category.newCategory("category", "category description"))
      );

      Assertions.assertThatThrownBy(() -> Entry.oneTimePaymentCreditExpenseEntry(command))
        .isInstanceOf(DomainConstraintViolationException.class)
        .hasMessageContaining(DomainValidationMessages.ENTRY_DESCRIPTION_NOT_BLANK);
    }

    @Test
    @DisplayName("Should not create an One Time Payment Credit Entry without amount")
    void test4() {
      final var expectedIssuedAt = IssuedAt.of(LocalDateTime.now().minusDays(10));
      final var expectedTitle = "title";
      final var expectedDescription = "description";

      final var command = new NewOneTimePaymentCreditExpenseEntry(
        expectedTitle,
        expectedDescription,
        null,
        expectedIssuedAt,
        Set.of(Category.newCategory("category", "category description"))
      );

      Assertions.assertThatThrownBy(() -> Entry.oneTimePaymentCreditExpenseEntry(command))
        .isInstanceOf(DomainConstraintViolationException.class)
        .hasMessageContaining(DomainValidationMessages.ENTRY_AMOUNT_NOT_NULL);
    }

    @Test
    @DisplayName("Should not create an One Time Payment Credit Entry without issuedAt")
    void test5() {
      final var expectedAmount = Amount.of(100);
      final var expectedTitle = "title";
      final var expectedDescription = "description";

      final var command = new NewOneTimePaymentCreditExpenseEntry(
        expectedTitle,
        expectedDescription,
        expectedAmount,
        null,
        Set.of(Category.newCategory("category", "category description"))
      );

      Assertions.assertThatThrownBy(() -> Entry.oneTimePaymentCreditExpenseEntry(command))
        .isInstanceOf(DomainConstraintViolationException.class)
        .hasMessageContaining(DomainValidationMessages.ENTRY_ISSUED_AT_NOT_NULL);
    }

    @Test
    @DisplayName("Should not create an One Time Payment Credit Entry with categories null")
    void test6() {
      final var expectedIssuedAt = IssuedAt.of(LocalDateTime.now().minusDays(10));
      final var expectedAmount = Amount.of(100);
      final var expectedTitle = "title";
      final var expectedDescription = "description";

      final var command = new NewOneTimePaymentCreditExpenseEntry(
        expectedTitle,
        expectedDescription,
        expectedAmount,
        expectedIssuedAt,
        null
      );

      Assertions.assertThatThrownBy(() -> Entry.oneTimePaymentCreditExpenseEntry(command))
        .isInstanceOf(DomainConstraintViolationException.class)
        .hasMessageContaining(DomainValidationMessages.ENTRY_CATEGORIES_NOT_EMPTY);
    }

  }

}
