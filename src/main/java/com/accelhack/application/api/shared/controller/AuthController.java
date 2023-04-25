package com.accelhack.application.api.shared.controller;

import com.accelhack.application.api.shared.controller.base.ExternalController;
import com.accelhack.application.api.shared.domain.user.User;
import com.accelhack.application.api.shared.domain.user.UserRepository;
import com.accelhack.application.api.shared.enums.Actor;
import com.accelhack.application.api.shared.model.*;
import com.accelhack.application.api.shared.service.JwtUserDetailsService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AuthController extends ExternalController {

  private final JwtUserDetailsService userDetailsService;

  private final UserRepository repository;

  @PostMapping("/refreshToken")
  @ResponseBody
  public String refreshToken(@RequestBody AuthorizationRequest request) {
    final DecodedJWT jwt = JWT.decode(request.getAccessToken());
    if (userDetailsService.isValidAccessToken(jwt)) {
      // jwt token is still alive
      return request.getAccessToken();
    }

    final String username = jwt.getSubject();
    return userDetailsService.reissueToken(username, request.getRefreshToken()).getAccessToken();
  }

  /**
   * curl -X POST -d '{"username":"manager1", "password": "123456"}' -H "accept: application/json"
   * -H "Content-Type: application/json" "http://localhost:8080/api/v1/user/add"
   */
  @PostMapping("/user/add")
  @ResponseBody
  public AuthenticationRequest refreshToken(@RequestBody AuthenticationRequest request) {
    repository.add(new User(request.getUsername(), request.getPassword(), Actor.USER),
        new Operator(null));
    return request;
  }
}
