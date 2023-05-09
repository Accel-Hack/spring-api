package com.accelhack.spring.api.base.auth.domain.user;

import com.accelhack.spring.api.base.auth.domain.User;
import com.accelhack.spring.api.shared.model.Operator;

public interface UserRepository {

  User findByUsername(String username);

  void add(User user, Operator operator);

  User save(User user, Operator operator);
}
