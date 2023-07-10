package com.accelhack.spring.api.presentation.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.accelhack.commons.model.Request;
import com.accelhack.commons.model.ResponseSet;
import com.accelhack.spring.api.presentation.model.AuthenticationModel;
import com.accelhack.spring.api.presentation.model.AuthorizationModel;

public interface AuthController {

  @PutMapping("/auth/user")
  @ResponseBody
  ResponseSet<AuthenticationModel.Response> user(
      @RequestBody Request<AuthenticationModel.Request> request);

  @PostMapping("/auth/token")
  @ResponseBody
  ResponseSet<AuthorizationModel.AccessToken> token(
      @RequestBody Request<AuthorizationModel.Request> request);
}
