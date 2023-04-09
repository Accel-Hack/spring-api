package com.accelhack.application.api.shared.domain.user;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserRepositoryImpl implements UserRepository {

  @Override
  public Optional<User> findById(int id) {
    return Optional.empty();
  }

  @Override
  public User add(User user) {
    return null;
  }

  @Override
  public User save(User user) {
    return null;
  }
}
