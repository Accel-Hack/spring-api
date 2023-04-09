package com.accelhack.application.api.shared.domain.user;

import com.accelhack.application.api.shared.mapper.UserMapper;
import com.accelhack.application.api.shared.model.Operator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

  private final UserMapper mapper;

  @Override
  public Optional<User> findById(int id) {
    return Optional.empty();
  }

  @Override
  public User add(User user, Operator operator) {
    mapper.add(user, operator);
    return user;
  }

  @Override
  public User save(User user) {
    return null;
  }
}
