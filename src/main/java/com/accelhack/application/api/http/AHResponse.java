package com.accelhack.application.api.http;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Getter
@Setter(AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AHResponse<E> {

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Boolean operationStatus;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private E result;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private List<AHResponseError> errors;

  private AHResponse(Builder<E> builder) {
    this.operationStatus = builder.getOperationStatus();
    this.result = builder.getResult();
    this.errors = builder.getErrors();
  }

  public static <E> AHResponse<E> ok(E result) {
    return (new Builder<E>()).operationStatus(true).result(result).build();
  }

  public static <E> AHResponse<E> error(AHResponseError error) {
    return error(List.of(error));
  }

  public static <E> AHResponse<E> error(List<AHResponseError> errors) {
    return (new Builder<E>()).operationStatus(false).errors(errors).build();
  }

  @NoArgsConstructor
  static class Builder<BE> extends AHResponse<BE> {
    Builder<BE> operationStatus(Boolean operationStatus) {
      this.setOperationStatus(operationStatus);
      return this;
    }

    Builder<BE> errors(List<AHResponseError> errors) {
      this.setErrors(errors);
      return this;
    }

    Builder<BE> result(BE result) {
      this.setResult(result);
      return this;
    }

    AHResponse<BE> build() {
      return new AHResponse<>(this);
    }
  }
}
