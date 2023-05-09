package com.accelhack.spring.api.shared.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
@Getter
public class AuthorizationConfiguration {
  private final PasswordEncoder encoder;

  @Value("${jwt.authorization.access-token.secret}")
  private String accessTokenSecret;

  @Value("${jwt.authorization.access-token.expire-seconds}")
  private long accessTokenExpireSeconds;

  @Value("${jwt.authorization.refresh-token.length}")
  public int refreshTokenLength;

  @Value("${jwt.authorization.refresh-token.expire-days}")
  private long refreshTokenExpireDays;
}
