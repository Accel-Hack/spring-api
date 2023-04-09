package com.accelhack.application.api.shared.domain.user;

import com.accelhack.application.api.shared.model.Operator;

import java.util.Optional;

public interface UserRepository {

  Optional<User> findById(int id);

  User add(User user, Operator operator);

  User save(User user);
}
