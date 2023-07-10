package com.accelhack.spring.api.domain.repository;

import com.accelhack.spring.api.domain.model.shared.Operator;
import com.accelhack.spring.api.domain.model.user.User;

public interface UserRepository {

  User findByUsername(String username);

  void add(User user, Operator operator);

  User save(User user, Operator operator);
}
