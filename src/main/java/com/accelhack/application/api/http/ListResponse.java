package com.accelhack.application.api.http;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Deprecated
@Getter
@Setter(AccessLevel.PACKAGE)
@NoArgsConstructor
public class ListResponse<E> {
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private int total;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private int start;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private List<E> items;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  public int count() {
    return items.size();
  }

  private ListResponse(Builder<E> builder) {
    this.total = builder.total;
    this.start = builder.start;
    this.items = builder.items;
  }

  public static <E> ListResponse<E> build(int total, int start, List<E> items) {
    return (new Builder<E>()).total(total).start(start).items(items).build();
  }

  @NoArgsConstructor
  public static class Builder<B> {
    private int total;
    private int start;
    private List<B> items;

    private Builder<B> total(int total) {
      this.total = total;
      return this;
    }

    private Builder<B> start(int start) {
      this.start = start;
      return this;
    }

    private Builder<B> items(List<B> items) {
      this.items = items;
      return this;
    }

    private ListResponse<B> build() {
      return new ListResponse<B>(this);
    }
  }
}
