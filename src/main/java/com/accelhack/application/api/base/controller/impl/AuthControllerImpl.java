package com.accelhack.application.api.base.controller.impl;

import com.accelhack.application.api.base.controller.AuthController;
import com.accelhack.application.api.base.controller.base.ExternalController;
import com.accelhack.application.api.base.model.AuthenticationModel;
import com.accelhack.application.api.base.model.AuthorizationModel;
import com.accelhack.application.api.base.usecase.UserUsecase;
import com.accelhack.application.api.http.AHRequest;
import com.accelhack.application.api.http.AHResponseSet;
import com.accelhack.application.api.shared.utils.ValidatorUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class AuthControllerImpl extends ExternalController implements AuthController {

  private final ValidatorUtils validatorUtils;
  private final UserUsecase userUsecase;

  @Override
  public AHResponseSet<AuthenticationModel.Response> user(
      AHRequest<AuthenticationModel.Request> request) {
    // validation
    AHResponseSet<AuthenticationModel.Response> error = validatorUtils.validate(request);
    if (Objects.nonNull(error))
      return error;

    AuthenticationModel.Response response = userUsecase.addUser(request.getOperand());

    return AHResponseSet.ok(response);
  }

  @Override
  public AHResponseSet<AuthorizationModel.AccessToken> token(
      AHRequest<AuthorizationModel.Request> request) {
    // validation
    AHResponseSet<AuthorizationModel.AccessToken> error = validatorUtils.validate(request);
    if (Objects.nonNull(error))
      return error;

    AuthorizationModel.AccessToken response = userUsecase.getAccessToken(request.getOperand());

    return AHResponseSet.ok(response);
  }
}
