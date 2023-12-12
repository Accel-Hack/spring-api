package com.accelhack.spring.api.base.auth.domain.user;

import com.accelhack.spring.api.base.auth.domain.User;

public interface UserFactory {
  User create(String username, String password);
}
