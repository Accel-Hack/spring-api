package com.accelhack.application.api.base.factory;

import com.accelhack.application.api.base.domain.User;
import com.accelhack.application.api.base.domain.user.UserFactory;
import com.accelhack.application.api.base.enums.Actor;
import com.accelhack.application.api.shared.config.AuthorizationConfiguration;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class UserFactoryImpl implements UserFactory {
  private final PasswordEncoder passwordEncoder;

  @Override
  public User create(String username, String password) {
    return User.builder().username(username).encryptPassword(passwordEncoder.encode(password))
        .actor(Actor.USER).build();
  }
}
