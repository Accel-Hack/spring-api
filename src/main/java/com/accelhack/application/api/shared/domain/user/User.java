package com.accelhack.application.api.shared.domain.user;

import com.accelhack.accelparts.utils.RandomUtils;
import com.accelhack.application.api.shared.config.SpringContext;
import com.accelhack.application.api.shared.enums.Actor;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static com.accelhack.application.api.shared.service.JwtUserDetailsService.REFRESH_TOKEN_LENGTH;
import static org.springframework.security.core.userdetails.User.builder;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE) // for JPA
public class User {

  @Getter(lazy = true, value = AccessLevel.PRIVATE)
  private final PasswordEncoder passwordEncoder = SpringContext.getBean(BCryptPasswordEncoder.class);

  private int id;
  private String username;
  @Getter(AccessLevel.NONE)
  private String password;
  private String encryptPassword;
  private Actor actor;
  private String resetCode;
  private Instant resetUntil;
  private List<Token> tokens;

  public User(String username, String password, Actor actor) {
    this.username = username;
    this.password = password;
    this.encryptPassword = getPasswordEncoder().encode(password);
    this.actor = actor;
  }

  public UserDetails toUserDetails() {
    return builder()
      .username(username)
      .password(encryptPassword)
      .authorities(List.of(actor.toAuthority()))
      .build();
  }

  public Token reissueToken(String refreshToken) {
    // 1. check refresh token
    if (!hasValidRefreshToken(refreshToken)) {
      throw new BadCredentialsException("Invalid refresh token");
    }

    // 2. remove old tokens from list
    tokens.removeIf(token ->
      getPasswordEncoder().matches(refreshToken, token.encryptRefreshToken));

    // 3. generate new token
    return issueToken(refreshToken);
  }

  public Token issueToken() {
    return issueToken(null);
  }

  private Token issueToken(String refreshToken) {
    // 0. constant variables (FIXME: move to config)
    long accessTokenExpSec = 30L;
    long refreshTokenExpDays = 15L;
    String accessTokenSecret = "MY_SECRET_KEY";

    // 1. generate new access token
    final Instant now = Instant.now();
    final String accessToken = JWT.create()
      .withSubject(username)
      .withIssuedAt(Date.from(now))
      .withExpiresAt(Date.from(now.plus(accessTokenExpSec, ChronoUnit.SECONDS)))
      .sign(Algorithm.HMAC512(accessTokenSecret.getBytes()));

    // 2. generate new refresh token
    final String _refreshToken;
    if (Objects.nonNull(refreshToken)) {
      _refreshToken = refreshToken;
    } else {
      _refreshToken = RandomUtils.alphaNumeric(REFRESH_TOKEN_LENGTH);
    }

    // 3. instant new token
    Token token = Token.builder()
      .accessToken(accessToken)
      .refreshToken(_refreshToken)
      .encryptRefreshToken(getPasswordEncoder().encode(_refreshToken))
      .expiresAt(now.plus(refreshTokenExpDays, ChronoUnit.DAYS))
      .build();

    // 4. add new token to list
    tokens.add(token);

    // 5. return new token
    return token;
  }

  private boolean hasValidRefreshToken(String refreshToken) {
    return tokens.stream().anyMatch(token ->
      getPasswordEncoder().matches(refreshToken, token.encryptRefreshToken));
  }

  @Builder(access = AccessLevel.PRIVATE)
  @AllArgsConstructor(access = AccessLevel.PRIVATE)
  @NoArgsConstructor(access = AccessLevel.PRIVATE) // for JPA
  static public class Token {
    @Getter
    @JsonIgnore
    private int id;
    @Getter
    private String accessToken;
    @Getter
    private String refreshToken;
    private String encryptRefreshToken;
    private Instant expiresAt;
  }
}
