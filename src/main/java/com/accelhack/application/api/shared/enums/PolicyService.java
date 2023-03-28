package com.accelhack.application.api.shared.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PolicyService implements NumericEnum<PolicyService> {
  API(10),
  SCREEN(20),
  CONTROL(30);
  private final int code;
}
