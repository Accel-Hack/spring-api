package com.accelhack.application.api.shared.mapper;

import com.accelhack.application.api.shared.domain.user.User;
import com.accelhack.application.api.shared.model.Operator;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

  User selectById(int id);

  int add(User user, Operator operator);

//  int addToken(User.Token token, Operator operator);

//  int deleteToken(User.Token token, Operator operator);
}
