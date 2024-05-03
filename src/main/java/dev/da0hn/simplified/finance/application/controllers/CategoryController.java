package dev.da0hn.simplified.finance.application.controllers;

import dev.da0hn.simplified.finance.application.controllers.dto.ApiCollectionResponse;
import dev.da0hn.simplified.finance.application.controllers.dto.ApiDataResponse;
import dev.da0hn.simplified.finance.application.controllers.dto.CreateCategoryParameters;
import dev.da0hn.simplified.finance.application.controllers.dto.ResourceCreated;
import dev.da0hn.simplified.finance.application.controllers.dto.ResourceSummary;
import dev.da0hn.simplified.finance.application.controllers.dto.SearchCategoriesParameters;
import dev.da0hn.simplified.finance.application.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

  private final CategoryService categoryService;

  @PostMapping
  public ResponseEntity<ApiDataResponse<ResourceCreated>> createCategory(
    @RequestBody final CreateCategoryParameters parameters
  ) {

    final var resourceCreated = this.categoryService.createCategory(parameters);

    return ResponseEntity.ok(
      ApiDataResponse.<ResourceCreated>builder()
        .data(resourceCreated)
        .message("Category created successfully")
        .build()
    );
  }

  @GetMapping
  public ResponseEntity<ApiCollectionResponse<ResourceSummary>> searchCategories(
    @RequestParam(required = false, name = "query-id") final String queryId,
    @RequestParam(required = false, name = "query-name") final String queryName,
    @RequestParam(required = false, name = "query-description") final String queryDescription,
    @RequestParam(required = false, name = "query-text") final String queryText
  ) {

    final var resourceSummaries = this.categoryService.searchCategories(
      SearchCategoriesParameters.builder()
        .id(queryId)
        .name(queryName)
        .description(queryDescription)
        .text(queryText)
        .build()
    );

    return ResponseEntity.ok(
      ApiCollectionResponse.<ResourceSummary>builder()
        .data(resourceSummaries)
        .build()
    );
  }

}
