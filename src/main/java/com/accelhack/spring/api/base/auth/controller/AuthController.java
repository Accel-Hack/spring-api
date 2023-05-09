package com.accelhack.spring.api.base.auth.controller;

import com.accelhack.spring.api.base.auth.model.AuthenticationModel;
import com.accelhack.spring.api.base.auth.model.AuthorizationModel;
import com.accelhack.commons.model.Request;
import com.accelhack.commons.model.ResponseSet;
import org.springframework.web.bind.annotation.*;

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
