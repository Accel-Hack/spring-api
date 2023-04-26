package com.accelhack.application.api.http;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Deprecated
@Getter
@Setter(AccessLevel.PACKAGE)
public class Response<E> {
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Integer operationKey;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Boolean operationStatus;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private E result;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private List<ResponseError> errors;

  private Response(Builder<E> builder) {
    this.operationKey = builder.operationKey;
    this.operationStatus = builder.operationStatus;
    this.result = builder.result;
    this.errors = builder.errors;
  }

  public static <E> Response<E> withResult(Integer key, E result) {
    return (new Builder<E>()).operationKey(key).operationStatus(true).result(result).build();
  }

  public static <E> Response<E> withResult(Long key, E result) {
    return withResult(Math.toIntExact(key), result);
  }

  // public static <E> Response<E> withResult(E result) {
  // return Response.withResult(1, result);
  // }

  public static <E> Response<E> withError(Integer key, List<ResponseError> errors) {
    return (new Builder<E>()).operationKey(key).operationStatus(false).errors(errors).build();
  }

  public static <E> Response<E> withError(Long key, List<ResponseError> errors) {
    return Response.withError(Math.toIntExact(key), errors);
  }

  public static <E> Response<E> withError(List<ResponseError> errors) {
    return Response.withError(1, errors);
  }


  @NoArgsConstructor
  static class Builder<B> {
    private Integer operationKey;
    private Boolean operationStatus;
    private List<ResponseError> errors;
    private B result;

    Builder<B> operationKey(Integer operationKey) {
      this.operationKey = operationKey;
      return this;
    }

    Builder<B> operationStatus(Boolean operationStatus) {
      this.operationStatus = operationStatus;
      return this;
    }

    Builder<B> errors(List<ResponseError> errors) {
      this.errors = errors;
      return this;
    }

    Builder<B> result(B result) {
      this.result = result;
      return this;
    }

    Response<B> build() {
      return new Response<B>(this);
    }
  }
}
