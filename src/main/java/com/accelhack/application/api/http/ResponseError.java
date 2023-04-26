package com.accelhack.application.api.http;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;


@Getter
@Setter(AccessLevel.PACKAGE)
@AllArgsConstructor
public class ResponseError {
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String key;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Object value;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String code;

  public static ResponseError build(String key, Object value, String code) {
    return new ResponseError(key, value, code);
  }
}
