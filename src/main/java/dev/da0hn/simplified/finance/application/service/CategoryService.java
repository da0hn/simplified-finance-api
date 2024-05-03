package dev.da0hn.simplified.finance.application.service;

import dev.da0hn.simplified.finance.application.controllers.dto.CreateCategoryParameters;
import dev.da0hn.simplified.finance.application.controllers.dto.ResourceCreated;
import dev.da0hn.simplified.finance.application.controllers.dto.SearchCategoriesParameters;
import dev.da0hn.simplified.finance.application.controllers.dto.ResourceSummary;

import java.util.List;

public interface CategoryService {

  ResourceCreated createCategory(final CreateCategoryParameters request);

  List<ResourceSummary> searchCategories(SearchCategoriesParameters parameters);

}
