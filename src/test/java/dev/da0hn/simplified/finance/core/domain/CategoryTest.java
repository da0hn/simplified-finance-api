package dev.da0hn.simplified.finance.core.domain;

import dev.da0hn.simplified.finance.core.domain.exceptions.DomainConstraintViolationException;
import dev.da0hn.simplified.finance.core.domain.validation.DomainValidationMessages;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Category Domain tests")
class CategoryTest {

  @Test
  @DisplayName("Should create a new Category")
  void test1() {
    final var category = Category.newCategory("name", "description");

    Assertions.assertThat(category.categoryId()).isNotNull();
    Assertions.assertThat(category.name()).isEqualTo("name");
    Assertions.assertThat(category.description()).isEqualTo("description");
  }

  @Test
  @DisplayName("Should not create a new Category without name")
  void test2() {
    Assertions.assertThatThrownBy(() -> Category.newCategory("", "description"))
      .isInstanceOf(DomainConstraintViolationException.class)
      .hasMessageContaining(DomainValidationMessages.CATEGORY_NAME_NOT_BLANK);
  }

  @Test
  @DisplayName("Should not create a new Category without description")
  void test3() {
    Assertions.assertThatThrownBy(() -> Category.newCategory("name", ""))
      .isInstanceOf(DomainConstraintViolationException.class)
      .hasMessageContaining(DomainValidationMessages.CATEGORY_DESCRIPTION_NOT_BLANK);
  }

}
