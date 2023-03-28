package com.accelhack.application.api.shared.mapper;

import com.accelhack.application.api.shared.dto.RoleDto;
import com.accelhack.application.api.shared.model.Operator;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleMapper {
  int insert(RoleDto roleDto, Operator operator);

  int delete(RoleDto roleDto, Operator operator);
}
