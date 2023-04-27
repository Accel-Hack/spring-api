package com.accelhack.application.api.base.model;

import com.accelhack.application.api.base.domain.User;
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
    private final String refreshToken;

    public Response(Request request) {
      this.accessToken = request.getAccessToken();
      this.refreshToken = request.getRefreshToken();
    }

    public Response(User.Token token) {
      this.accessToken = token.getAccessToken();
      this.refreshToken = token.getRefreshToken();
    }
  }
}
