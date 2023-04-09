package com.accelhack.application.api.shared.mapper;

import com.accelhack.application.api.shared.domain.user.User;
import com.accelhack.application.api.shared.model.Operator;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

  User selectByUsername(String username);

  int add(User user, Operator operator);

  int update(User user, Operator operator);

  int addToken(int userId, User.Token token, Operator operator);

  int deleteToken(int userTokenId, Operator operator);
}
