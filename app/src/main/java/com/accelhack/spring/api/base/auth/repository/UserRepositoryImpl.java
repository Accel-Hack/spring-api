package com.accelhack.spring.api.base.auth.repository;

import com.accelhack.spring.api.base.auth.domain.User;
import com.accelhack.spring.api.base.auth.domain.user.UserRepository;
import com.accelhack.spring.api.base.auth.dto.UserDto;
import com.accelhack.spring.api.base.auth.mapper.UserMapper;
import com.accelhack.spring.api.shared.model.Operator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

  private final UserMapper userMapper;

  @Override
  public User findByUsername(String username) {
    final UserDto userDto = userMapper.selectByUsername(username);
    if (Objects.isNull(userDto))
      return null;

    return userDto.toUserDomain();
  }

  @Override
  public void add(User user, Operator operator) {
    userMapper.save(UserDto.from(user), operator);
  }

  @Override
  public User save(User user, Operator operator) {
    // 0. get previous user info
    User prevUser = Optional.ofNullable(findByUsername(user.getUsername())).orElseThrow();

    // 1. update user info
    userMapper.save(UserDto.from(user), operator);

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
        .forEach(
            token -> userMapper.addToken(user.getId(), UserDto.TokenDto.from(token), operator));

    // return user
    return user;
  }
}
