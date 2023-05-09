package com.accelhack.spring.api.base.auth.usecase;

import com.accelhack.spring.api.base.auth.model.AuthenticationModel;
import com.accelhack.spring.api.base.auth.model.AuthorizationModel;
import org.springframework.transaction.annotation.Transactional;

public interface UserUsecase {

  @Transactional(rollbackFor = Throwable.class)
  AuthorizationModel.AccessToken getAccessToken(AuthorizationModel.Request request);

  @Transactional(rollbackFor = Throwable.class)
  AuthenticationModel.Response addUser(AuthenticationModel.Request request);

  @Transactional(rollbackFor = Throwable.class)
  AuthorizationModel.Response login(String username);
}
