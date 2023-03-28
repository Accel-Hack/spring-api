package com.accelhack.application.api.shared.controller;

import com.accelhack.application.api.shared.controller.base.ExternalController;
import com.accelhack.application.api.shared.entity.User;
import com.accelhack.application.api.shared.service.JwtUserDetailsService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class AuthController extends ExternalController {

  private final JwtUserDetailsService userDetailsService;

  @PostMapping("/refreshToken")
  public User.Token refreshToken(@RequestBody User.Token token) {
    final DecodedJWT jwt = JWT.decode(token.getAccessToken());
    if (userDetailsService.verifyAccessToken(jwt)) {
      return token;
    }

    final String username = jwt.getSubject();
    if (!userDetailsService.verifyRefreshToken(username, token.getRefreshToken())) {
      throw new BadCredentialsException("Invalid refresh token!");
    }

    return userDetailsService.issueToken(username);
  }
}
