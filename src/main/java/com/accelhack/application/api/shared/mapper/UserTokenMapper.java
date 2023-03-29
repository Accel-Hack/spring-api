package com.accelhack.application.api.shared.mapper;

import com.accelhack.application.api.shared.dto.UserTokenDto;
import com.accelhack.application.api.shared.model.Operator;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserTokenMapper {
  List<UserTokenDto> selectByUserId(int userId);

  int insert(UserTokenDto userTokenDto, Operator operator);

  int delete(UserTokenDto userTokenDto, Operator operator);
}
