package com.accelhack.spring.api.base.auth.model;

import com.accelhack.commons.model.Operand;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

public class AuthorizationModel {
  @Getter
  public static class Request implements Operand {
    @NotEmpty
    private String accessToken;
    @NotEmpty
    private String refreshToken;
  }

  @Getter
  public static class Response {
    private final String accessToken;
    private String refreshToken;

    public Response(String accessToken, String refreshToken) {
      this.accessToken = accessToken;
      this.refreshToken = refreshToken;
    }

    public Response(String accessToken) {
      this.accessToken = accessToken;
    }
  }

  public record AccessToken(String token) {}
}
