package com.accelhack.application.api.shared.model;

import lombok.Data;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Collections;

@Data
public class AuthenticationRequest {
  private String username;
  private String password;

  public UsernamePasswordAuthenticationToken toToken() {
    return new UsernamePasswordAuthenticationToken(username, password, Collections.emptyList());
  }
}
