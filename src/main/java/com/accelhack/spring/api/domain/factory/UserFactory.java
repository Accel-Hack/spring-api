package com.accelhack.spring.api.domain.factory;

import com.accelhack.spring.api.domain.model.user.User;

public interface UserFactory {
  User create(String username, String password);
}
