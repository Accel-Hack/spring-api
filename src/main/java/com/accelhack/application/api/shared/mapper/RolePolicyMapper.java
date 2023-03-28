package com.accelhack.application.api.shared.mapper;

import com.accelhack.application.api.shared.dto.RolePolicyDto;
import com.accelhack.application.api.shared.model.Operator;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RolePolicyMapper {
  List<RolePolicyDto> insert(List<RolePolicyDto> rolePolicyDtoList, Operator operator);

  RolePolicyDto delete(RolePolicyDto rolePolicyDto, Operator operator);
}
