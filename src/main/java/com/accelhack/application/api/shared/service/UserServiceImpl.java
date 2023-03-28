package com.accelhack.application.api.shared.service;

import com.accelhack.application.api.shared.dto.UserDto;
import com.accelhack.application.api.shared.dto.UserTokenDto;
import com.accelhack.application.api.shared.mapper.UserMapper;
import com.accelhack.application.api.shared.mapper.UserTokenMapper;
import com.accelhack.application.api.shared.model.Operator;
import com.accelhack.application.api.shared.model.UserSelector;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
  private final UserMapper userMapper;
  private final UserTokenMapper userTokenMapper;

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
  public UserTokenDto getToken(String userName, String refreshToken) {
    UserDto userDto = getByUsername(userName);
    return userTokenMapper.selectBy(userDto.getId(), refreshToken);
  }

  @Override
  public UserTokenDto addAuthToken(UserTokenDto userTokenDto, Operator operator) {
    userTokenMapper.delete(userTokenDto.toDelete(), operator);

    UserTokenDto userToken = userTokenDto.toCreate();
    userTokenMapper.insert(userToken, operator);
    return userToken;
  }
}
