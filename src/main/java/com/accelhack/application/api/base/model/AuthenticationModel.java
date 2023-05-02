package com.accelhack.application.api.base.model;

import com.accelhack.commons.model.Operand;
import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Collections;

public class AuthenticationModel {

  @Getter
  public static class Request implements Operand {
    private String username;
    private String password;

    public UsernamePasswordAuthenticationToken toToken() {
      return new UsernamePasswordAuthenticationToken(username, password, Collections.emptyList());
    }
  }

  @Getter
  public static class Response {
    private final String username;

    public Response(Request request) {
      this.username = request.getUsername();
    }
  }
}
