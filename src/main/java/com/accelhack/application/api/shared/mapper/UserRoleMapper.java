package com.accelhack.application.api.shared.mapper;

import com.accelhack.application.api.shared.dto.UserRoleDto;
import com.accelhack.application.api.shared.model.Operator;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRoleMapper {
  int insert(UserRoleDto userRoleDto, Operator operator);

  int delete(UserRoleDto userRoleDto, Operator operator);
}
