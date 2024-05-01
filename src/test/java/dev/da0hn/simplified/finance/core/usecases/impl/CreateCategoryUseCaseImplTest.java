package dev.da0hn.simplified.finance.core.usecases.impl;

import dev.da0hn.simplified.finance.core.domain.Category;
import dev.da0hn.simplified.finance.core.ports.api.usecases.CreateCategoryUseCase;
import dev.da0hn.simplified.finance.core.ports.spi.repositories.CategoryRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@DisplayName("Create Category Use Case Tests")
class CreateCategoryUseCaseImplTest {

  private CreateCategoryUseCase sut;

  @Mock
  private CategoryRepository categoryRepository;

  @BeforeEach
  void setUp() {
    this.sut = new CreateCategoryUseCaseImpl(this.categoryRepository);
  }

  @Test
  @DisplayName("Should create a category")
  void test1() {

    final var input = CreateCategoryUseCase.Input.builder()
      .categoryName("categoryName")
      .categoryDescription("categoryDescription")
      .build();

    Mockito.doReturn(Optional.empty())
      .when(this.categoryRepository)
      .findByName(Mockito.eq(input.categoryName()));

    Mockito.doNothing()
      .when(this.categoryRepository)
      .create(Mockito.any(Category.class));

    final var output = this.sut.execute(input);

    Assertions.assertThat(output).isNotNull();
    Mockito.verify(this.categoryRepository, Mockito.times(1))
      .findByName(Mockito.eq(input.categoryName()));
    Mockito.verify(this.categoryRepository, Mockito.times(1))
      .create(Mockito.any(Category.class));
  }

  @Test
  @DisplayName("Should throw an exception when category already exists")
  void test2() {

    final var input = CreateCategoryUseCase.Input.builder()
      .categoryName("categoryName")
      .categoryDescription("categoryDescription")
      .build();

    Mockito.doReturn(Optional.of(Category.newCategory(input.categoryName(), input.categoryDescription())))
      .when(this.categoryRepository)
      .findByName(Mockito.eq(input.categoryName()));

    Assertions.assertThatThrownBy(() -> this.sut.execute(input))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessage("Category with name categoryName already exists");

    Mockito.verify(this.categoryRepository, Mockito.times(1))
      .findByName(Mockito.eq(input.categoryName()));
    Mockito.verify(this.categoryRepository, Mockito.never())
      .create(Mockito.any(Category.class));
  }

}
