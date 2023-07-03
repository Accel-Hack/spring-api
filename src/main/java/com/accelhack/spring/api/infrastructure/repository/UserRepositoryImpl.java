package com.accelhack.spring.api.infrastructure.repository;

import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.accelhack.spring.api.domain.model.shared.Operator;
import com.accelhack.spring.api.domain.model.user.User;
import com.accelhack.spring.api.domain.repository.UserRepository;
import com.accelhack.spring.api.infrastructure.dto.UserDto;
import com.accelhack.spring.api.infrastructure.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

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
