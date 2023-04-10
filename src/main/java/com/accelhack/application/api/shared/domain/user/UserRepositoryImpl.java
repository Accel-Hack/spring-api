package com.accelhack.application.api.shared.domain.user;

import com.accelhack.application.api.shared.mapper.UserMapper;
import com.accelhack.application.api.shared.model.Operator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

  private final UserMapper userMapper;

  @Override
  public Optional<User> findByUsername(String username) {
    return Optional.ofNullable(userMapper.selectByUsername(username));
  }

  @Override
  public User add(User user, Operator operator) {
    userMapper.add(user, operator);
    return user;
  }

  @Override
  public User save(User user, Operator operator) {
    // 0. get previous user info
    User prevUser = findByUsername(user.getUsername()).orElseThrow();

    // 1. update user info
    userMapper.update(user, operator);

    // 2. update user token
    // 2.1. delete old token
    prevUser.getTokens().stream().filter(
      token -> user.getTokens().stream().map(User.Token::getId).noneMatch(id -> id == token.getId())
    ).forEach(
      token -> userMapper.deleteToken(token.getId(), operator)
    );

    // 2.2. add new token
    user.getTokens().stream().filter(
      token -> prevUser.getTokens().stream().map(User.Token::getId).noneMatch(id -> id == token.getId())
    ).forEach(
      token -> userMapper.addToken(user.getId(), token, operator)
    );

    // return user
    return user;
  }
}
