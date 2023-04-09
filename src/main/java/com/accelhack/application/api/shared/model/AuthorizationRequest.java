package com.accelhack.application.api.shared.model;

import lombok.Data;

@Data
public class AuthorizationRequest {
  private String accessToken;
  private String refreshToken;
}
