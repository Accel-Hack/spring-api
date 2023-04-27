package com.accelhack.application.api.shared.model;

import com.accelhack.application.api.base.domain.User;
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
