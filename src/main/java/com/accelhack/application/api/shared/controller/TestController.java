package com.accelhack.application.api.shared.controller;

import com.accelhack.application.api.shared.controller.base.ExternalController;
import com.accelhack.application.api.shared.entity.User;
import com.accelhack.application.api.shared.enums.Actor;
import com.accelhack.application.api.shared.model.Operator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class TestController extends ExternalController {

  private final com.accelhack.application.api.shared.domain.user.User.CreateBuilder createBuilder;
  private final com.accelhack.application.api.shared.domain.user.UserRepository repository;

  /**
   curl -X POST -d '{"username":"manager1", "password": "123456"}' \
   -H "accept: application/json" -H "Content-Type: application/json" \
   "http://localhost:8080/api/v1/user/add"
   * @param authentication
   * @return
   */
  @PostMapping("/user/add")
  @ResponseBody
  public User.Authentication refreshToken(@RequestBody User.Authentication authentication) {
    repository.add(createBuilder.create(
        authentication.getUsername(),
        authentication.getPassword(),
        Actor.USER)
      , new Operator(null));
    return authentication;
  }
}
