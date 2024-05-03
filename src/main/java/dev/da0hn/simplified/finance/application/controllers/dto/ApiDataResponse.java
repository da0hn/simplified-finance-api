package dev.da0hn.simplified.finance.application.controllers.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Value;

import java.time.Instant;

@Value
@Builder
@JsonPropertyOrder({ "message", "timestamp", "data" })
public class ApiDataResponse<T> {

  T data;

  String message;

  @Builder.Default
  Instant timestamp = Instant.now();

  public static ApiDataResponse<Void> onlyMessage(final String message) {
    return ApiDataResponse.<Void>builder()
      .message(message)
      .build();
  }

}
