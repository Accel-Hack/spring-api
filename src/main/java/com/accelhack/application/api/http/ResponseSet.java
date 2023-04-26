package com.accelhack.application.api.http;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.*;

@Getter
@Setter(AccessLevel.PACKAGE)
@NoArgsConstructor
public class ResponseSet<E> {

  private Long timestamp = new Date().getTime();

  // 共通項目
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private HttpStatus status;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Integer statusCode;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String message; // 20221114: 今はいらない

  // 成功した時の項目
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Integer total;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private List<Response<E>> results;

  // エラー時の項目
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private List<ResponseError> errors;

  public boolean hasErrors() {
    return Objects.nonNull(errors) && !errors.isEmpty();
  }

  private ResponseSet(Builder<E> builder) {
    // 共通項目
    this.status = builder.status;
    this.statusCode = builder.status.value();
    this.message = builder.message;
    this.total = builder.total;
    this.results = builder.results;
    this.errors = builder.errors;
  }

  public String encode() throws JsonProcessingException {
    JsonMapper mapper = new JsonMapper();
    mapper.registerModule(new JavaTimeModule());
    mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    return mapper.writeValueAsString(this);
  }

  public static <E> ResponseSet<E> ok(E entity) {
    return ResponseSet.ok(1, Collections.singletonList(Response.withResult(1, entity)));
  }

  public static <E> ResponseSet<E> ok(Response<E> result) {
    return ResponseSet.ok(1, Collections.singletonList(result));
  }

  public static <E> ResponseSet<E> ok(Integer total, List<Response<E>> results) {
    return (new Builder<E>()).status(HttpStatus.OK).total(total).results(results).build();
  }

  public static <E> ResponseSet<E> error(HttpStatus status, List<ResponseError> errors) {
    return (new Builder<E>()).status(status).errors(errors).build();
  }

  public static <E> ResponseSet<E> internalServerError() {
    return (new Builder<E>()).status(HttpStatus.INTERNAL_SERVER_ERROR).build();
  }

  @NoArgsConstructor
  static class Builder<B> {
    private HttpStatus status;
    private String message; // 20221114: 今はいらない

    // 成功した時の項目
    private Integer total;
    private List<Response<B>> results;

    // エラー時の項目
    private List<ResponseError> errors;

    Builder<B> status(HttpStatus status) {
      this.status = status;
      return this;
    }

    Builder<B> message(String message) {
      this.message = message;
      return this;
    }

    Builder<B> total(Integer total) {
      this.total = total;
      return this;
    }

    Builder<B> results(List<Response<B>> results) {
      this.results = results;
      return this;
    }

    Builder<B> errors(List<ResponseError> errors) {
      this.errors = errors;
      return this;
    }

    ResponseSet<B> build() {
      return new ResponseSet<B>(this);
    }
  }
}
