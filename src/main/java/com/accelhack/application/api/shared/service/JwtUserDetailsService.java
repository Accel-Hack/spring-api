package com.accelhack.application.api.shared.service;

import com.accelhack.accelparts.utils.RandomUtils;
import com.accelhack.application.api.shared.dto.UserDto;
import com.accelhack.application.api.shared.dto.UserTokenDto;
import com.accelhack.application.api.shared.entity.User.Token;
import com.accelhack.application.api.shared.model.Operator;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

  private final PasswordEncoder passwordEncoder;

  @Value("${jwt.access-token.expiration-seconds}")
  private long accessTokenExpSec;

  @Value("${jwt.refresh-token.expiration-days}")
  private long refreshTokenExpDays;

  @Value("${jwt.access-token.secret-key}")
  private String accessTokenSecret;
  private static final int REFRESH_TOKEN_LENGTH = 24;
  private final UserService userService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    final UserDto userDto = findByUsername(username);
    return org.springframework.security.core.userdetails.User.builder()
      .username(userDto.getUsername())
      .password(userDto.getPassword())
      .authorities(List.of(userDto.getActor().toAuthority()))
      .build();
  }

  public Token reissueToken(String username, String refreshToken) throws UsernameNotFoundException {
    final UserDto userDto = findByUsername(username);
    userService.removeAuthToken(username, refreshToken, new Operator(userDto));
    return issueToken(username);
  }

  public Token issueToken(String username) throws UsernameNotFoundException {
    final UserDto userDto = findByUsername(username);
    final Instant now = Instant.now();
    final String accessToken = JWT.create()
      .withSubject(userDto.getUsername())
      .withIssuedAt(Date.from(now))
      .withExpiresAt(Date.from(now.plus(accessTokenExpSec, ChronoUnit.SECONDS)))
      .sign(Algorithm.HMAC512(accessTokenSecret.getBytes()));

    final String refreshToken = RandomUtils.alphaNumeric(REFRESH_TOKEN_LENGTH);

    final UserTokenDto userTokenDto = UserTokenDto.builder()
      .userId(userDto.getId())
      .accessToken(accessToken)
      .refreshToken(passwordEncoder.encode(refreshToken))
      .expiresAt(now.plus(refreshTokenExpDays, ChronoUnit.DAYS))
      .build();
    userService.addAuthToken(userTokenDto, new Operator(userDto));

    return Token.builder()
      .accessToken(accessToken)
      .refreshToken(refreshToken)
      .build();
  }

  public boolean isValidAccessToken(DecodedJWT jwt) {
    try {
      JWT.require(Algorithm.HMAC512(accessTokenSecret.getBytes())).build().verify(jwt);
    } catch (TokenExpiredException e) {
      return false;
    }
    return true;
  }

  public boolean isValidRefreshToken(String username, String refreshToken) throws UsernameNotFoundException {
    final List<UserTokenDto> userTokenDtoList = userService.getTokens(username);
    return userTokenDtoList.stream().anyMatch(userTokenDto -> passwordEncoder.matches(refreshToken, userTokenDto.getRefreshToken()));
  }

  private UserDto findByUsername(String username) throws UsernameNotFoundException {
    final UserDto userDto = userService.getByUsername(username);
    if (Objects.isNull(userDto)) {
      throw new UsernameNotFoundException("User not found:[%s]".formatted(username));
    }
    return userDto;
  }
}
