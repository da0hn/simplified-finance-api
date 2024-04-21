package dev.da0hn.simplified.finance.core.domain;

import dev.da0hn.simplified.finance.core.domain.commands.NewDebitEntryCommand;
import dev.da0hn.simplified.finance.core.domain.exceptions.DomainConstraintViolationException;
import dev.da0hn.simplified.finance.core.domain.validation.DomainValidationMessages;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Set;

@DisplayName("Entry Domain tests")
class EntryTest {

  @Nested
  @DisplayName("Debit Entry tests")
  class DebitEntryTests {

    @Test
    @DisplayName("Should create a new Debit entry")
    void test1() {
      final var expectedIssuedAt = LocalDateTime.now().minusDays(10);
      final var expectedAmount = Amount.of(10.0);
      final var expectedStatus = EntryStatus.PENDING;
      final var expectedType = EntryType.EXPENSE;
      final var expectedTitle = "title";
      final var expectedDescription = "description";

      final var command = new NewDebitEntryCommand(
        expectedTitle,
        expectedDescription,
        expectedAmount,
        expectedType,
        expectedStatus,
        expectedIssuedAt,
        Set.of(new Category(1L, "category", "category description"))
      );

      final var debitEntry = Entry.debitEntry(command);

      Assertions.assertThat(debitEntry).isNotNull();
      Assertions.assertThat(debitEntry.maybeId()).isEmpty();
      Assertions.assertThat(debitEntry.title()).isEqualTo(expectedTitle);
      Assertions.assertThat(debitEntry.description()).isEqualTo(expectedDescription);
      Assertions.assertThat(debitEntry.type()).isEqualTo(expectedType);
      Assertions.assertThat(debitEntry.status()).isEqualTo(expectedStatus);
      Assertions.assertThat(debitEntry.amount()).isEqualTo(expectedAmount);
      Assertions.assertThat(debitEntry.paymentMethod()).isEqualTo(PaymentMethod.DEBIT);
      Assertions.assertThat(debitEntry.createdAt()).isNotNull();
      Assertions.assertThat(debitEntry.issuedAt()).isEqualTo(expectedIssuedAt);
      Assertions.assertThat(debitEntry.installmentDetails()).isEmpty();
      Assertions.assertThat(debitEntry.categories()).hasSize(1);
    }

    @Test
    @DisplayName("Should not create an Debit Entry without title")
    void test2() {
      final var expectedIssuedAt = LocalDateTime.now().minusDays(10);
      final var expectedAmount = Amount.of(10.0);
      final var expectedStatus = EntryStatus.PENDING;
      final var expectedType = EntryType.EXPENSE;
      final var expectedDescription = "description";

      final var command = new NewDebitEntryCommand(
        null,
        expectedDescription,
        expectedAmount,
        expectedType,
        expectedStatus,
        expectedIssuedAt,
        Set.of(new Category(1L, "category", "category description"))
      );

      Assertions.assertThatThrownBy(() -> Entry.debitEntry(command))
        .isInstanceOf(DomainConstraintViolationException.class)
        .hasMessageContaining(DomainValidationMessages.ENTRY_TITLE_NOT_BLANK);
    }

    @Test
    @DisplayName("Should not create an Debit Entry without description")
    void test3() {
      final var expectedIssuedAt = LocalDateTime.now().minusDays(10);
      final var expectedAmount = Amount.of(10.0);
      final var expectedStatus = EntryStatus.PENDING;
      final var expectedType = EntryType.EXPENSE;
      final var expectedTitle = "title";

      final var command = new NewDebitEntryCommand(
        expectedTitle,
        null,
        expectedAmount,
        expectedType,
        expectedStatus,
        expectedIssuedAt,
        Set.of(new Category(1L, "category", "category description"))
      );

      Assertions.assertThatThrownBy(() -> Entry.debitEntry(command))
        .isInstanceOf(DomainConstraintViolationException.class)
        .hasMessageContaining(DomainValidationMessages.ENTRY_DESCRIPTION_NOT_BLANK);
    }

    @Test
    @DisplayName("Should not create an Debit Entry without amount")
    void test4() {
      final var expectedIssuedAt = LocalDateTime.now().minusDays(10);
      final var expectedStatus = EntryStatus.PENDING;
      final var expectedType = EntryType.EXPENSE;
      final var expectedTitle = "title";
      final var expectedDescription = "description";

      final var command = new NewDebitEntryCommand(
        expectedTitle,
        expectedDescription,
        null,
        expectedType,
        expectedStatus,
        expectedIssuedAt,
        Set.of(new Category(1L, "category", "category description"))
      );

      Assertions.assertThatThrownBy(() -> Entry.debitEntry(command))
        .isInstanceOf(DomainConstraintViolationException.class)
        .hasMessageContaining(DomainValidationMessages.ENTRY_AMOUNT_NOT_NULL);
    }

    @Test
    @DisplayName("Should not create an Debit Entry without type")
    void test5() {
      final var expectedIssuedAt = LocalDateTime.now().minusDays(10);
      final var expectedAmount = Amount.of(10.0);
      final var expectedStatus = EntryStatus.PENDING;
      final var expectedTitle = "title";
      final var expectedDescription = "description";

      final var command = new NewDebitEntryCommand(
        expectedTitle,
        expectedDescription,
        expectedAmount,
        null,
        expectedStatus,
        expectedIssuedAt,
        Set.of(new Category(1L, "category", "category description"))
      );

      Assertions.assertThatThrownBy(() -> Entry.debitEntry(command))
        .isInstanceOf(DomainConstraintViolationException.class)
        .hasMessageContaining(DomainValidationMessages.ENTRY_TYPE_NOT_NULL);
    }

    @Test
    @DisplayName("Should not create an Debit Entry without status")
    void test6() {
      final var expectedIssuedAt = LocalDateTime.now().minusDays(10);
      final var expectedAmount = Amount.of(10.0);
      final var expectedType = EntryType.EXPENSE;
      final var expectedTitle = "title";
      final var expectedDescription = "description";

      final var command = new NewDebitEntryCommand(
        expectedTitle,
        expectedDescription,
        expectedAmount,
        expectedType,
        null,
        expectedIssuedAt,
        Set.of(new Category(1L, "category", "category description"))
      );

      Assertions.assertThatThrownBy(() -> Entry.debitEntry(command))
        .isInstanceOf(DomainConstraintViolationException.class)
        .hasMessageContaining(DomainValidationMessages.ENTRY_STATUS_NOT_NULL);
    }

    @Test
    @DisplayName("Should not create an Debit Entry without issuedAt")
    void test7() {
      final var expectedAmount = Amount.of(10.0);
      final var expectedStatus = EntryStatus.PENDING;
      final var expectedType = EntryType.EXPENSE;
      final var expectedTitle = "title";
      final var expectedDescription = "description";

      final var command = new NewDebitEntryCommand(
        expectedTitle,
        expectedDescription,
        expectedAmount,
        expectedType,
        expectedStatus,
        null,
        Set.of(new Category(1L, "category", "category description"))
      );

      Assertions.assertThatThrownBy(() -> Entry.debitEntry(command))
        .isInstanceOf(DomainConstraintViolationException.class)
        .hasMessageContaining(DomainValidationMessages.ENTRY_ISSUED_AT_NOT_NULL);
    }

    @Test
    @DisplayName("Should not create an Debit Entry without categories")
    void test8() {
      final var expectedIssuedAt = LocalDateTime.now().minusDays(10);
      final var expectedAmount = Amount.of(10.0);
      final var expectedStatus = EntryStatus.PENDING;
      final var expectedType = EntryType.EXPENSE;
      final var expectedTitle = "title";
      final var expectedDescription = "description";

      final var command = new NewDebitEntryCommand(
        expectedTitle,
        expectedDescription,
        expectedAmount,
        expectedType,
        expectedStatus,
        expectedIssuedAt,
        null
      );

      Assertions.assertThatThrownBy(() -> Entry.debitEntry(command))
        .isInstanceOf(DomainConstraintViolationException.class)
        .hasMessageContaining(DomainValidationMessages.ENTRY_CATEGORIES_NOT_EMPTY);
    }

    @Test
    @DisplayName("Should not create an Debit Entry without categories")
    void test9() {
      final var expectedIssuedAt = LocalDateTime.now().minusDays(10);
      final var expectedAmount = Amount.of(10.0);
      final var expectedStatus = EntryStatus.PENDING;
      final var expectedType = EntryType.EXPENSE;
      final var expectedTitle = "title";
      final var expectedDescription = "description";

      final var command = new NewDebitEntryCommand(
        expectedTitle,
        expectedDescription,
        expectedAmount,
        expectedType,
        expectedStatus,
        expectedIssuedAt,
        Set.of()
      );

      Assertions.assertThatThrownBy(() -> Entry.debitEntry(command))
        .isInstanceOf(DomainConstraintViolationException.class)
        .hasMessageContaining(DomainValidationMessages.ENTRY_CATEGORIES_NOT_EMPTY);
    }

    @Test
    @DisplayName("Should not create an Debit Entry with null command")
    void test10() {
      Assertions.assertThatThrownBy(() -> Entry.debitEntry(null))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("NewDebitEntryCommand must not be null");
    }

  }

}
