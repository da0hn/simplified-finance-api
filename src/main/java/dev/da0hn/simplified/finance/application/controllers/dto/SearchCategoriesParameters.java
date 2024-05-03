package dev.da0hn.simplified.finance.application.controllers.dto;

import lombok.Builder;

@Builder
public record SearchCategoriesParameters(
  String id,
  String name,
  String description,
  String text
) {
}
