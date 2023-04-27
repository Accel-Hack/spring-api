package com.accelhack.application.api.base.domain;

import com.accelhack.accelparts.utils.RandomUtils;
import com.accelhack.application.api.base.enums.Actor;
import com.accelhack.application.api.shared.config.AuthorizationConfiguration;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static org.springframework.security.core.userdetails.User.builder;

@Getter
@Setter
@NoArgsConstructor
public class User {

  private UUID id;
  private String username;
  private String encryptPassword;
  private Actor actor;
  private String resetCode;
  private Instant resetUntil;
  private List<Token> tokens;

  @Getter
  @Builder(access = AccessLevel.PRIVATE)
  @AllArgsConstructor(access = AccessLevel.PRIVATE)
  @NoArgsConstructor
  static public class Token {
    @JsonIgnore
    private UUID id;
    private String accessToken;
    private String refreshToken;
    private String encryptRefreshToken;
    private Instant expiresAt;
  }

  public UserDetails toUserDetails() {
    return builder().username(username).password(encryptPassword)
        .authorities(List.of(actor.toAuthority())).build();
  }

  public Token reissueToken(AuthorizationConfiguration config, String refreshToken) {
    // 1. check refresh token
    if (tokens.stream()
        .noneMatch(token -> config.getEncoder().matches(refreshToken, token.encryptRefreshToken))) {
      throw new BadCredentialsException("Invalid refresh token");
    }

    // 2. remove old tokens from list
    tokens.removeIf(token -> config.getEncoder().matches(refreshToken, token.encryptRefreshToken));

    // 3. generate new token
    return issueToken(config, refreshToken);
  }

  public Token issueToken(AuthorizationConfiguration config) {
    return issueToken(config, null);
  }

  private Token issueToken(AuthorizationConfiguration config, String refreshToken) {

    // 1. generate new access token
    final Instant now = Instant.now();
    final String accessToken = JWT.create().withSubject(username).withIssuedAt(Date.from(now))
        .withExpiresAt(
            Date.from(now.plus(config.getAccessTokenExpireSeconds(), ChronoUnit.SECONDS)))
        .sign(Algorithm.HMAC512(config.getAccessTokenSecret().getBytes()));

    // 2. generate new refresh token
    final String _refreshToken;
    if (Objects.nonNull(refreshToken)) {
      _refreshToken = refreshToken;
    } else {
      _refreshToken = RandomUtils.alphaNumeric(config.getRefreshTokenLength());
    }

    // 3. instant new token
    Token token = Token.builder().id(UUID.randomUUID()).accessToken(accessToken)
        .refreshToken(_refreshToken).encryptRefreshToken(config.getEncoder().encode(_refreshToken))
        .expiresAt(now.plus(config.getRefreshTokenExpireDays(), ChronoUnit.DAYS)).build();

    // 4. add new token to list
    tokens.add(token);

    // 5. return new token
    return token;
  }
}
