package com.accelhack.application.api.shared.service;

import com.accelhack.application.api.shared.dto.UserDto;
import com.accelhack.application.api.shared.dto.UserTokenDto;
import com.accelhack.application.api.shared.mapper.UserMapper;
import com.accelhack.application.api.shared.mapper.UserTokenMapper;
import com.accelhack.application.api.shared.model.Operator;
import com.accelhack.application.api.shared.model.UserSelector;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
  private final UserMapper userMapper;
  private final UserTokenMapper userTokenMapper;
  private final PasswordEncoder passwordEncoder;
  @Override
  public UserDto getByUsername(String userName) {
    return userMapper.selectByUsername(userName);
  }

  @Override
  public List<UserDto> search(UserSelector selector) {
    return userMapper.selectBy(selector);
  }

  @Override
  public UserDto create(UserDto userDto, Operator operator) {
    UserDto user = userDto.toCreate();
    userMapper.insert(user, operator);
    return user;
  }

  @Override
  public List<UserTokenDto> getTokens(String userName) {
    UserDto userDto = getByUsername(userName);
    return userTokenMapper.selectByUserId(userDto.getId());
  }

  @Override
  public void addAuthToken(UserTokenDto userTokenDto, Operator operator) {
    UserTokenDto userToken = userTokenDto.toCreate();
    userTokenMapper.insert(userToken, operator);
  }

  @Override
  public void removeAuthToken(String username, String refreshToken, Operator operator) {
    final UserDto userDto = getByUsername(username);
    final List<UserTokenDto> userTokenDtoList = userTokenMapper.selectByUserId(userDto.getId());
    userTokenDtoList.stream()
      .filter(token -> passwordEncoder.matches(refreshToken, token.getRefreshToken()))
      .forEach(token -> userTokenMapper.delete(token.toDelete(), operator));
  }
}
