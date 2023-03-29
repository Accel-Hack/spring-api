package com.accelhack.application.api.shared.service;

import com.accelhack.application.api.shared.dto.UserDto;
import com.accelhack.application.api.shared.dto.UserTokenDto;
import com.accelhack.application.api.shared.model.Operator;
import com.accelhack.application.api.shared.model.UserSelector;

import java.util.List;

public interface UserService {
  UserDto getByUsername(String userName);

  List<UserDto> search(UserSelector selector);

  UserDto create(UserDto userDto, Operator operator);

  List<UserTokenDto> getTokens(String userName);

  void addAuthToken(UserTokenDto userTokenDto, Operator operator);

  void removeAuthToken(String username, String refreshToken, Operator operator);
}
