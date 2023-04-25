package com.accelhack.application.api.shared.model;

import com.accelhack.application.api.shared.dto.UserDto;
import lombok.Getter;

import java.util.Objects;
import java.util.Optional;

public class Operator {
  @Getter
  private final String code;

  private Operator(String code) {
    this.code = code;
  }

  public Operator(UserDto userDto) {
    this.code =
        Optional.ofNullable(userDto).map(UserDto::getId).map(Objects::toString).orElse("ANONYMOUS");
  }
}
