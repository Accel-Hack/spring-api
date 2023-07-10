package com.accelhack.spring.api.domain.model.shared;

import com.accelhack.spring.api.domain.model.user.User;

import lombok.Getter;

public class Operator {
  public static final Operator ANONYMOUS = new Operator("ANONYMOUS");
  @Getter
  private final String code;

  public Operator(String code) {
    this.code = code;
  }

  public Operator(User user) {
    this.code = user.getId().toString();
  }
}
