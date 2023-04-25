package com.accelhack.application.api.shared.service;

import com.accelhack.application.api.shared.domain.user.User;
import com.accelhack.application.api.shared.domain.user.UserRepository;
import com.accelhack.application.api.shared.model.Operator;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

  public static final int REFRESH_TOKEN_LENGTH = 24;
  private final UserRepository userRepository;
  @Value("${jwt.access-token.expiration-seconds}")
  private long accessTokenExpSec;
  @Value("${jwt.refresh-token.expiration-days}")
  private long refreshTokenExpDays;
  @Value("${jwt.access-token.secret-key}")
  private String accessTokenSecret;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return findByUsername(username).toUserDetails();
  }

  public User.Token reissueToken(String username, String refreshToken)
      throws UsernameNotFoundException {
    final User user = findByUsername(username);
    final User.Token token = user.reissueToken(refreshToken);
    userRepository.save(user, new Operator(null));
    return token;
  }

  public User.Token issueToken(String username) throws UsernameNotFoundException {
    final User user = findByUsername(username);
    final User.Token token = user.issueToken();
    userRepository.save(user, new Operator(null));
    return token;
  }

  public boolean isValidAccessToken(DecodedJWT jwt) {
    try {
      JWT.require(Algorithm.HMAC512(accessTokenSecret.getBytes())).build().verify(jwt);
    } catch (TokenExpiredException e) {
      return false;
    }
    return true;
  }

  private User findByUsername(String username) throws UsernameNotFoundException {
    final Optional<User> optUser = userRepository.findByUsername(username);
    return optUser.orElseThrow(
        () -> new UsernameNotFoundException("User not found:[%s]".formatted(username)));
  }
}
