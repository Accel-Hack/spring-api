package com.accelhack.application.api.base.model;

import com.accelhack.application.api.http.AHOperand;
import lombok.Getter;

public class AuthorizationModel {
  @Getter
  public static class Request implements AHOperand {
    private String accessToken;
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
