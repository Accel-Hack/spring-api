package com.accelhack.application.api.shared.domain.user;

import java.util.Optional;

public interface UserRepository {

  Optional<User> findById(int id);

  User add(User user);

  User save(User user);
}
