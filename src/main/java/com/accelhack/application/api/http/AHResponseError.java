package com.accelhack.application.api.http;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.ConstraintViolation;
import lombok.*;

@Getter
@Setter(AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AHResponseError {
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String key;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Object value;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String code;

  public static AHResponseError build(String key, Object value, String code) {
    return new AHResponseError(key, value, code);
  }

  public static AHResponseError build(ConstraintViolation<?> violation) {
    return new AHResponseError(violation.getPropertyPath().toString(), violation.getInvalidValue(),
        violation.getMessage());
  }
}
