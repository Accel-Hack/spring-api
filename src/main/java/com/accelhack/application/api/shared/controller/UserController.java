package com.accelhack.application.api.shared.controller;

import com.accelhack.accelparts.Request;
import com.accelhack.accelparts.ResponseSet;
import com.accelhack.accelparts.response.ListResponse;
import com.accelhack.application.api.shared.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserController {
  String CONTEXT_PATH = "user";

  @PostMapping(CONTEXT_PATH + "/search")
  ResponseEntity<ResponseSet<ListResponse<User>>> search(@RequestBody Request<UserSelector> userRequest);

  @PostMapping(CONTEXT_PATH + "/sudo/start")
  ResponseEntity<ResponseSet<Superuser>> startSudo(@RequestBody Request<Superuser> userRequest);

  @PostMapping(CONTEXT_PATH + "/sudo/end")
  ResponseEntity<ResponseSet<Superuser>> endSudo(@RequestBody Request<Superuser> userRequest);
}
