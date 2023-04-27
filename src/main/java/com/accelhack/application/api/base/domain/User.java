package com.accelhack.application.api.base.domain;

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
  @NoArgsConstructor
  static public class Token {
    @JsonIgnore
    private UUID id;
    private String accessToken;
    private String encryptRefreshToken;
    private Instant expiresAt;

    private Token(AuthorizationConfiguration config, String username, String encryptRefreshToken) {
      final Instant now = Instant.now();
      this.id = UUID.randomUUID();
      this.accessToken = JWT.create().withSubject(username).withIssuedAt(Date.from(now))
          .withExpiresAt(
              Date.from(now.plus(config.getAccessTokenExpireSeconds(), ChronoUnit.SECONDS)))
          .sign(Algorithm.HMAC512(config.getAccessTokenSecret().getBytes()));
      this.encryptRefreshToken = encryptRefreshToken;
      this.expiresAt = Instant.now().plus(config.getRefreshTokenExpireDays(), ChronoUnit.DAYS);
    }

    public Token issueAccessToken(AuthorizationConfiguration config, String username) {
      return new Token(config, username, encryptRefreshToken);
    }
  }

  public UserDetails toUserDetails() {
    return builder().username(username).password(encryptPassword)
        .authorities(List.of(actor.toAuthority())).build();
  }

  public Token reissueAccessToken(AuthorizationConfiguration config, String refreshToken) {
    // 1. check refresh token
    Token token = tokens.stream()
        .filter(t -> config.getEncoder().matches(refreshToken, t.encryptRefreshToken)).findFirst()
        .orElseThrow(() -> new BadCredentialsException("Invalid refresh token"));

    // 2. return expired message
    if (token.getExpiresAt().isBefore(Instant.now()))
      throw new BadCredentialsException("Refresh token has expired");

    // 3. remove old access token
    tokens.remove(token);

    // 4. generate new access token
    Token newToken = token.issueAccessToken(config, username);

    // 5. add new access token
    tokens.add(newToken);

    return newToken;
  }

  public Token issueToken(AuthorizationConfiguration config, String refreshToken) {
    // 1. instant new token
    Token token = new Token(config, username, config.getEncoder().encode(refreshToken));

    // 2. add new token to list
    tokens.add(token);

    // 3. return new token
    return token;
  }
}
