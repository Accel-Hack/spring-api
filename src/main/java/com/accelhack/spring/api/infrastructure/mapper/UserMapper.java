package com.accelhack.spring.api.infrastructure.mapper;

import java.util.UUID;

import org.apache.ibatis.annotations.Mapper;

import com.accelhack.spring.api.domain.model.shared.Operator;
import com.accelhack.spring.api.infrastructure.dto.UserDto;

@Mapper
public interface UserMapper {

  UserDto selectByUsername(String username);

  int save(UserDto userDto, Operator operator);

  void addToken(UUID userId, UserDto.TokenDto tokenDto, Operator operator);

  void deleteToken(UUID userTokenId, Operator operator);
}
