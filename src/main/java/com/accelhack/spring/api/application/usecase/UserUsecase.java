package com.accelhack.spring.api.application.usecase;

import org.springframework.transaction.annotation.Transactional;

import com.accelhack.spring.api.presentation.model.AuthenticationModel;
import com.accelhack.spring.api.presentation.model.AuthorizationModel;

public interface UserUsecase {

  @Transactional(rollbackFor = Throwable.class)
  AuthorizationModel.AccessToken getAccessToken(AuthorizationModel.Request request);

  @Transactional(rollbackFor = Throwable.class)
  AuthenticationModel.Response addUser(AuthenticationModel.Request request);

  @Transactional(rollbackFor = Throwable.class)
  AuthorizationModel.Response login(String username);
}
