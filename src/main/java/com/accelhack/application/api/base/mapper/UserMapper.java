package com.accelhack.application.api.base.mapper;

import com.accelhack.application.api.base.domain.User;
import com.accelhack.application.api.shared.model.Operator;
import org.apache.ibatis.annotations.Mapper;

import java.util.UUID;

@Mapper
public interface UserMapper {

  User selectByUsername(String username);

  int save(User user, Operator operator);

  void addToken(UUID userId, User.Token token, Operator operator);

  void deleteToken(UUID userTokenId, Operator operator);
}
