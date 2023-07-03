package com.accelhack.spring.api.domain.factory;

import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.accelhack.spring.api.domain.model.user.Actor;
import com.accelhack.spring.api.domain.model.user.User;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserFactoryImpl implements UserFactory {
  private final PasswordEncoder passwordEncoder;

  @Override
  public User create(String username, String password) {
    return User.builder().id(UUID.randomUUID()).username(username)
        .encryptPassword(passwordEncoder.encode(password)).actor(Actor.USER).build();
  }
}
