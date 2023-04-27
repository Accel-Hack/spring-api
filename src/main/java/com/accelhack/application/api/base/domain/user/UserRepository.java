package com.accelhack.application.api.base.domain.user;

import com.accelhack.application.api.base.domain.User;
import com.accelhack.application.api.shared.model.Operator;

public interface UserRepository {

  User findByUsername(String username);

  void add(User user, Operator operator);

  User save(User user, Operator operator);
}
