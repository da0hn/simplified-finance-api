package dev.da0hn.simplified.finance.core.domain;

import dev.da0hn.simplified.finance.core.domain.validation.DomainValidationMessages;
import dev.da0hn.simplified.finance.core.domain.validation.SelfValidating;
import dev.da0hn.simplified.finance.core.domain.valueobjects.CategoryId;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Category extends SelfValidating<Category> {

  @NotNull(message = DomainValidationMessages.CATEGORY_ID_NOT_NULL)
  private final CategoryId categoryId;

  @NotBlank(message = DomainValidationMessages.CATEGORY_NAME_NOT_BLANK)
  private final String name;

  @NotBlank(message = DomainValidationMessages.CATEGORY_DESCRIPTION_NOT_BLANK)
  private final String description;

  private Category(final CategoryId categoryId, final String name, final String description) {
    this.categoryId = categoryId;
    this.name = name;
    this.description = description;
    this.validateSelf();
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
