package com.accelhack.application.api.base.domain.user;

import com.accelhack.application.api.base.domain.User;

public interface UserFactory {
  User create(String username, String password);
}
