package com.accelhack.application.api.shared.mapper;

import com.accelhack.application.api.shared.dto.UserTokenDto;
import com.accelhack.application.api.shared.model.Operator;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserTokenMapper {
  UserTokenDto selectBy(int userId, String refreshToken);

  int insert(UserTokenDto userTokenDto, Operator operator);

  int delete(UserTokenDto userTokenDto, Operator operator);
}
