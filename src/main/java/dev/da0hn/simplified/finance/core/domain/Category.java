package dev.da0hn.simplified.finance.core.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Category {

  private final Long id;

  @NotNull
  @NotBlank
  private final String name;

  @NotNull(message = "description must not be null")
  @NotBlank(message = "description must not be blank")
  private final String description;

  public Category(final Long id, final String name, final String description) {
    this.id = id;
    this.name = name;
    this.description = description;
  }

  public Category newCategory(final String name, final String description) {
    return new Category(null, name, description);
  }

  public Long id() {
    return this.id;
  }

  public String name() {
    return this.name;
  }

  public String description() {
    return this.description;
  }

}
