package com.accelhack.application.api.base.controller;

import com.accelhack.application.api.base.model.AuthenticationModel;
import com.accelhack.application.api.base.model.AuthorizationModel;
import com.accelhack.application.api.http.AHRequest;
import com.accelhack.application.api.http.AHResponseSet;
import org.springframework.web.bind.annotation.*;

public interface AuthController {

  @PutMapping("/auth/user")
  @ResponseBody
  AHResponseSet<AuthenticationModel.Response> user(
      @RequestBody AHRequest<AuthenticationModel.Request> request);

  @PostMapping("/auth/token")
  @ResponseBody
  AHResponseSet<AuthorizationModel.Response> token(
      @RequestBody AHRequest<AuthorizationModel.Request> request);
}
