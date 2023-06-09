package com.accelhack.spring.api.base.auth.usecase.impl;

import com.accelhack.spring.api.base.auth.model.AuthenticationModel;
import com.accelhack.spring.api.base.auth.model.AuthorizationModel;
import com.accelhack.spring.api.shared.config.AuthorizationConfiguration;
import com.accelhack.spring.api.shared.model.Operator;
import com.accelhack.spring.api.base.auth.domain.User;
import com.accelhack.spring.api.base.auth.domain.user.UserFactory;
import com.accelhack.spring.api.base.auth.domain.user.UserRepository;
import com.accelhack.spring.api.base.auth.usecase.UserUsecase;
import com.accelhack.commons.utils.RandomUtils;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class UserUsecaseImpl implements UserUsecase {

  private final AuthorizationConfiguration config;
  private final UserRepository userRepository;
  private final UserFactory userFactory;

  @Override
  public AuthorizationModel.AccessToken getAccessToken(AuthorizationModel.Request request) {
    final DecodedJWT jwt = JWT.decode(request.getAccessToken());
    if (isValidAccessToken(jwt)) {
      // jwt token is still alive
      return new AuthorizationModel.AccessToken(request.getAccessToken());
    }

    final String username = jwt.getSubject();
    final User user = userRepository.findByUsername(username);
    if (Objects.isNull(user))
      throw new UsernameNotFoundException("User not found:[%s]".formatted(username));

    final User.Token token = user.reissueAccessToken(config, request.getRefreshToken());

    userRepository.save(user, new Operator(user));
    return new AuthorizationModel.AccessToken(token.getAccessToken());
  }

  @Override
  public AuthenticationModel.Response addUser(AuthenticationModel.Request request) {
    User user = userFactory.create(request.getUsername(), request.getPassword());
    userRepository.add(user, Operator.ANONYMOUS);
    return new AuthenticationModel.Response(request);
  }

  @Override
  public AuthorizationModel.Response login(String username) {
    final User user = userRepository.findByUsername(username);
    if (Objects.isNull(user))
      throw new UsernameNotFoundException("User not found:[%s]".formatted(username));

    final String refreshToken = RandomUtils.alphaNumeric(config.getRefreshTokenLength());
    final Pair<User, User.Token> pair = user.issueToken(config, refreshToken);
    userRepository.save(pair.getLeft(), Operator.ANONYMOUS);

    return new AuthorizationModel.Response(pair.getRight().getAccessToken(), refreshToken);
  }

  private boolean isValidAccessToken(DecodedJWT jwt) {
    try {
      JWT.require(Algorithm.HMAC512(config.getAccessTokenSecret().getBytes())).build().verify(jwt);
    } catch (TokenExpiredException e) {
      return false;
    }
    return true;
  }
}
