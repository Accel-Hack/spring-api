package com.accelhack.application.api.base.mapper;

import com.accelhack.application.api.base.dto.UserDto;
import com.accelhack.application.api.shared.model.Operator;
import org.apache.ibatis.annotations.Mapper;

import java.util.UUID;

@Mapper
public interface UserMapper {

  UserDto selectByUsername(String username);

  int save(UserDto userDto, Operator operator);

  void addToken(UUID userId, UserDto.TokenDto tokenDto, Operator operator);

  void deleteToken(UUID userTokenId, Operator operator);
}
