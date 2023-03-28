package com.accelhack.application.api.shared.mapper;

import com.accelhack.application.api.shared.dto.SuperuserDto;
import com.accelhack.application.api.shared.model.Operator;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SuperuserMapper {
  SuperuserDto selectById(int id);

  int insert(SuperuserDto superuserDto, int expireMinutes, Operator operator);

  int update(SuperuserDto superuserDto, int expireMinutes, Operator operator);

  int delete(SuperuserDto superuserDto, Operator operator);
}
