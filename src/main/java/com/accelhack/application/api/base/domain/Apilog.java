package com.accelhack.application.api.base.domain;

import com.accelhack.application.api.shared.config.MyContext;
import com.accelhack.application.api.shared.model.Operator;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Instant;
import java.util.*;

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

  public static class ApilogBuilder {
    public Apilog build() {
      // set default values
      if (Objects.isNull(id))
        id = UUID.randomUUID();
      if (Objects.isNull(operationTime))
        operationTime = Instant.now();
      // return domain via validation
      return validate(new Apilog(id, operator, sessionId, remoteAddress, operationTime,
          executionDurationMs, method, path, query, body, response, status, exception));
    }

    public Apilog validate(final Apilog apilog) {
      final Validator validator = MyContext.getBean(Validator.class);
      final Set<ConstraintViolation<Apilog>> errors = validator.validate(apilog);
      if (!errors.isEmpty()) {
        throw new IllegalArgumentException(errors.toString());
      }
      return apilog;
    }
  }
}
