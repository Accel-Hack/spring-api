package com.accelhack.application.api.base.usecase;

import com.accelhack.application.api.base.model.AuthenticationModel;
import com.accelhack.application.api.base.model.AuthorizationModel;
import org.springframework.transaction.annotation.Transactional;

public interface UserUsecase {

  @Transactional(rollbackFor = Throwable.class)
  AuthorizationModel.Response getAccessToken(AuthorizationModel.Request request);

  @Transactional(rollbackFor = Throwable.class)
  AuthenticationModel.Response addUser(AuthenticationModel.Request request);

  @Transactional(rollbackFor = Throwable.class)
  AuthorizationModel.Response login(String username);
}
