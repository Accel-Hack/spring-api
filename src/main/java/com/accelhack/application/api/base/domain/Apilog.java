package com.accelhack.application.api.base.domain;

import com.accelhack.application.api.shared.model.Operator;
import com.accelhack.application.api.shared.utils.BuilderUtils;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static java.lang.Math.round;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
public class Apilog {
  @NotNull
  private final UUID id;
  @NotNull
  private final Operator operator;
  @NotBlank
  private final String sessionId;
  @NotBlank
  private final String remoteAddress;
  @NotNull
  private final Instant operationTime;
  private final double executionDurationMs;
  @NotBlank
  private final String method;
  @NotBlank
  private final String path;
  private final String query;
  private final String body;
  private final String response;
  private final Integer status;
  private final String exception;

  public Apilog finished(HttpServletResponse response, Exception exception) {
    return toBuilder()
        .executionDurationMs(round(Instant.now().getNano() - operationTime.getNano() / 1_000_000.0))
        .status(response.getStatus())
        // FIXME: set response body
        .exception(Optional.ofNullable(exception).map(Throwable::getLocalizedMessage).orElse(null))
        .build();
  }

  public static ApilogBuilder builder() {
    return new ApilogBuilder() {
      @Override
      public Apilog build() {
        // return domain via validation
        return BuilderUtils.validate(super.build());
      }
    };
  }
}
