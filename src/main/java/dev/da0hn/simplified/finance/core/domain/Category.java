package dev.da0hn.simplified.finance.core.domain;

import dev.da0hn.simplified.finance.core.domain.valueobjects.CategoryId;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Category {

  @NotNull
  private final CategoryId categoryId;

  @NotNull
  @NotBlank
  private final String name;

  @NotNull(message = "description must not be null")
  @NotBlank(message = "description must not be blank")
  private final String description;

  public Category(final CategoryId categoryId, final String name, final String description) {
    this.categoryId = categoryId;
    this.name = name;
    this.description = description;
  }

  public static Category newCategory(final String name, final String description) {
    return new Category(
      CategoryId.newInstance(),
      name,
      description
    );
  }

  public CategoryId categoryId() {
    return this.categoryId;
  }

  public String name() {
    return this.name;
  }

  public String description() {
    return this.description;
  }

}
