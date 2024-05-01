package dev.da0hn.simplified.finance.core.usecases.impl;

import dev.da0hn.simplified.finance.core.domain.Category;
import dev.da0hn.simplified.finance.core.ports.api.usecases.SearchCategoriesUseCase;
import dev.da0hn.simplified.finance.core.ports.spi.repositories.CategoryRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
@DisplayName("Search Categories Use Case Tests")
class SearchCategoriesUseCaseImplTest {

  private SearchCategoriesUseCase sut;

  @Mock
  private CategoryRepository categoryRepository;

  @BeforeEach
  void setUp() {
    this.sut = new SearchCategoriesUseCaseImpl(this.categoryRepository);
  }

  @Test
  @DisplayName("Should search categories")
  void test1() {
    final var input = new SearchCategoriesUseCase.Input("queryName", "queryDescription", "queryText");

    Mockito.doReturn(List.of(Category.newCategory("name1", "description1")))
      .when(this.categoryRepository)
      .searchBy(Mockito.eq(input.queryName()), Mockito.eq(input.queryDescription()), Mockito.eq(input.queryText()));

    final var output = this.sut.execute(input);

    Assertions.assertThat(output).isNotNull();
    Assertions.assertThat(output.categories()).isNotNull();
    Assertions.assertThat(output.categories()).hasSize(1);
  }

}
