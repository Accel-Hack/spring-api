package com.accelhack.application.api.shared.mapper;

import com.accelhack.application.api.shared.dto.UserDto;
import com.accelhack.application.api.shared.model.Operator;
import com.accelhack.application.api.shared.model.UserSelector;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DomainUserMapper {
  UserDto selectByUsername(String userName);

  List<UserDto> selectBy(UserSelector selector);

  int insert(UserDto userDto, Operator operator);
}
