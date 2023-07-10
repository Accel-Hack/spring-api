package com.accelhack.spring.api.presentation.controller;

import java.util.Objects;

import org.springframework.stereotype.Controller;

import com.accelhack.commons.model.Request;
import com.accelhack.commons.model.ResponseSet;
import com.accelhack.commons.model.utils.ValidatorUtils;
import com.accelhack.spring.api.application.usecase.UserUsecase;
import com.accelhack.spring.api.presentation.model.AuthenticationModel;
import com.accelhack.spring.api.presentation.model.AuthorizationModel;

import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AuthControllerImpl extends ExternalController implements AuthController {

  private final Validator validator;
  private final UserUsecase userUsecase;

  @Override
  public ResponseSet<AuthenticationModel.Response> user(
      Request<AuthenticationModel.Request> request) {
    // validation
    ResponseSet<AuthenticationModel.Response> error = ValidatorUtils.validate(validator, request);
    if (Objects.nonNull(error))
      return error;

    AuthenticationModel.Response response = userUsecase.addUser(request.getOperand());

    return ResponseSet.ok(response);
  }

  @Override
  public ResponseSet<AuthorizationModel.AccessToken> token(
      Request<AuthorizationModel.Request> request) {
    // validation
    ResponseSet<AuthorizationModel.AccessToken> error = ValidatorUtils.validate(validator, request);
    if (Objects.nonNull(error))
      return error;

    AuthorizationModel.AccessToken response = userUsecase.getAccessToken(request.getOperand());

    return ResponseSet.ok(response);
  }
}
