package com.accelhack.application.api.shared.mapper;

import com.accelhack.application.api.shared.dto.PolicyDto;
import com.accelhack.application.api.shared.model.Operator;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PolicyMapper {
  int insert(PolicyDto policyDto, Operator operator);

  int delete(PolicyDto policyDto, Operator operator);
}
