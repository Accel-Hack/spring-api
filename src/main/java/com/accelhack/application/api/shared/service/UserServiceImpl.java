package com.accelhack.application.api.shared.service;

import com.accelhack.application.api.shared.dto.UserDto;
import com.accelhack.application.api.shared.dto.UserRoleDto;
import com.accelhack.application.api.shared.mapper.UserMapper;
import com.accelhack.application.api.shared.mapper.UserRoleMapper;
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
  private final UserRoleMapper userRoleMapper;

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
  public UserRoleDto addRole(UserRoleDto userRoleDto, Operator operator) {
    UserRoleDto userRole = userRoleDto.toCreate();
    userRoleMapper.insert(userRole, operator);
    return userRole;
  }
}
