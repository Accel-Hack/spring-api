package com.accelhack.application.api.shared.entity;

import com.accelhack.accelparts.Operand;
import lombok.*;

public class User  {
  @NoArgsConstructor
  @AllArgsConstructor
  @Data
  @Builder
  public static class Authentication implements Operand {
    private String username;
    private String password;
  }

  @NoArgsConstructor
  @AllArgsConstructor
  @Data
  @Builder
  public static class Token implements Operand {
    private String accessToken;
    private String refreshToken;
  }
}
