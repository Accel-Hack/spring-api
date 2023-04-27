package com.accelhack.application.api.base.repository;

import com.accelhack.application.api.base.domain.User;
import com.accelhack.application.api.base.domain.user.UserRepository;
import com.accelhack.application.api.base.mapper.UserMapper;
import com.accelhack.application.api.shared.model.Operator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

  private final UserMapper userMapper;

  @Override
  public User findByUsername(String username) {
    return userMapper.selectByUsername(username);
  }

  @Override
  public void add(User user, Operator operator) {
    userMapper.save(user, operator);
  }

  @Override
  public User save(User user, Operator operator) {
    // 0. get previous user info
    User prevUser = Optional.ofNullable(findByUsername(user.getUsername())).orElseThrow();

    // 1. update user info
    userMapper.save(user, operator);

    // 2. update user token
    // 2.1. delete old token
    prevUser.getTokens().stream()
        .filter(token -> user.getTokens().stream().map(User.Token::getId)
            .noneMatch(id -> id.equals(token.getId())))
        .forEach(token -> userMapper.deleteToken(token.getId(), operator));

    // 2.2. add new token
    user.getTokens().stream()
        .filter(token -> prevUser.getTokens().stream().map(User.Token::getId)
            .noneMatch(id -> id.equals(token.getId())))
        .forEach(token -> userMapper.addToken(user.getId(), token, operator));

    // return user
    return user;
  }
}
