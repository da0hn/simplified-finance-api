package dev.da0hn.simplified.finance.application.controllers.dto;

import java.io.Serializable;

public record CreateCategoryParameters(String categoryName, String categoryDescription) implements Serializable {
}
