package com.accelhack.spring.api.base.auth.domain;

import com.accelhack.spring.api.shared.config.AuthorizationConfiguration;
import com.accelhack.spring.api.shared.utils.BuilderUtils;
import com.accelhack.spring.api.base.auth.enums.Actor;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
public class User {

  @NotNull
  private final UUID id;
  @NotBlank
  private final String username;
  @NotBlank
  private final String encryptPassword;
  @NotNull
  private final Actor actor;
  private final String resetCode;
  private final Instant resetUntil;
  @NotNull
  @Builder.Default
  private final List<Token> tokens = Collections.emptyList();

  public static UserBuilder builder() {
    return new UserBuilder() {
      @Override
      public User build() {
        // return domain via validation
        return BuilderUtils.validate(super.build());
      }
    };
  }

  @Getter
  @AllArgsConstructor(access = AccessLevel.PRIVATE)
  @Builder(toBuilder = true)
  static public class Token {
    @NotNull
    @JsonIgnore
    private final UUID id;
    @NotBlank
    private final String accessToken;
    @NotBlank
    private final String encryptRefreshToken;
    @NotNull
    private final Instant expiresAt;

    public static TokenBuilder builder() {
      return new TokenBuilder() {
        @Override
        public Token build() {
          // return domain via validation
          return BuilderUtils.validate(super.build());
        }
      };
    }

    public static class TokenBuilder {
      private TokenBuilder accessTokenWithExpires(AuthorizationConfiguration config,
          String username) {
        final Instant now = Instant.now();
        this.accessToken = JWT.create().withSubject(username).withIssuedAt(Date.from(now))
            .withExpiresAt(
                Date.from(now.plus(config.getAccessTokenExpireSeconds(), ChronoUnit.SECONDS)))
            .sign(Algorithm.HMAC512(config.getAccessTokenSecret().getBytes()));
        this.expiresAt = now.plus(config.getRefreshTokenExpireDays(), ChronoUnit.DAYS);
        return this;
      }
    }

    public static Token issue(AuthorizationConfiguration config, String username,
        String encryptRefreshToken) {
      return User.Token.builder().id(UUID.randomUUID()).accessTokenWithExpires(config, username)
          .encryptRefreshToken(encryptRefreshToken).build();
    }

    public Token reissue(AuthorizationConfiguration config, String username) {
      return toBuilder().accessTokenWithExpires(config, username).build();
    }
  }

  public UserDetails toUserDetails() {
    return org.springframework.security.core.userdetails.User.builder().username(username)
        .password(encryptPassword).authorities(List.of(actor.toAuthority())).build();
  }

  public Token reissueAccessToken(AuthorizationConfiguration config, String refreshToken) {
    // 1. check refresh token
    final Token token = tokens.stream().sorted(Comparator.comparing(Token::getExpiresAt).reversed())
        .filter(t -> config.getEncoder().matches(refreshToken, t.encryptRefreshToken)).findFirst()
        .orElseThrow(() -> new BadCredentialsException("Invalid refresh token"));

    // 2. return expired message
    if (token.getExpiresAt().isBefore(Instant.now()))
      throw new BadCredentialsException("Refresh token has expired");

    // 3. remove old access token
    tokens.removeIf(t -> config.getEncoder().matches(refreshToken, t.encryptRefreshToken));

    // 4. generate new access token
    final Token newToken = token.reissue(config, username);

    // 5. add new access token
    tokens.add(newToken);

    return newToken;
  }

  public Pair<User, Token> issueToken(AuthorizationConfiguration config, String refreshToken) {
    // 1. instant new token
    final Token token = Token.issue(config, username, config.getEncoder().encode(refreshToken));

    // 2. add new token to list
    final User user =
        toBuilder().tokens(Stream.concat(tokens.stream(), Stream.of(token)).toList()).build();

    // 3. return new token
    return Pair.of(user, token);
  }
}
