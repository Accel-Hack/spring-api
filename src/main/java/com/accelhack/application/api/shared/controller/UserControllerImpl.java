package com.accelhack.application.api.shared.controller;

import com.accelhack.accelparts.Request;
import com.accelhack.accelparts.ResponseSet;
import com.accelhack.accelparts.response.ListResponse;
import com.accelhack.application.api.shared.controller.base.InternalController;
import com.accelhack.application.api.shared.model.*;
import com.accelhack.application.api.shared.functional.ParameterizedApi;
import com.accelhack.application.api.shared.transaction.SuperuserTransaction;
import com.accelhack.application.api.shared.transaction.UserTransaction;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class UserControllerImpl extends InternalController implements UserController {
  private final UserTransaction userTransaction;
  private final SuperuserTransaction superuserTransaction;

  @Override
  public ResponseEntity<ResponseSet<ListResponse<User>>> search(Request<UserSelector> userRequest) {
    ParameterizedApi<UserSelector, ListResponse<User>> callable = userTransaction::search;
    return execute(userRequest, callable);
  }

  @Override
  public ResponseEntity<ResponseSet<Superuser>> startSudo(Request<Superuser> userRequest) {
    ParameterizedApi<Superuser, Superuser> callable = superuserTransaction::startSession;
    return execute(userRequest, callable);
  }

  @Override
  public ResponseEntity<ResponseSet<Superuser>> endSudo(Request<Superuser> userRequest) {
    ParameterizedApi<Superuser, Superuser> callable = superuserTransaction::endSession;
    return execute(userRequest, callable);
  }
}
