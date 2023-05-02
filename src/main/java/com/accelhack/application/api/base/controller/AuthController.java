package com.accelhack.application.api.base.controller;

import com.accelhack.application.api.base.model.AuthenticationModel;
import com.accelhack.application.api.base.model.AuthorizationModel;
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
