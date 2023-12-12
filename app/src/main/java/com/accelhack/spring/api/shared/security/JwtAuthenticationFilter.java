package com.accelhack.spring.api.shared.security;

import com.accelhack.spring.api.base.auth.model.AuthenticationModel;
import com.accelhack.spring.api.base.auth.model.AuthorizationModel;
import com.accelhack.spring.api.base.auth.usecase.UserUsecase;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private final AuthenticationManager authenticationManager;
  private final UserUsecase userUsecase;
  private final ObjectMapper objectMapper;

  @Override
  public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
      throws AuthenticationException {
    final AuthenticationModel.Request request;
    try {
      request = objectMapper.readValue(req.getInputStream(), AuthenticationModel.Request.class);
    } catch (IOException e) {
      // unacceptable json request
      throw new BadCredentialsException("Invalid credentials", e);
    }
    return authenticationManager.authenticate(request.toToken());
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res,
      FilterChain chain, Authentication auth) throws IOException {
    final AuthorizationModel.Response response = userUsecase.login(auth.getName());
    final String json = objectMapper.writeValueAsString(response);

    res.setContentType(MediaType.APPLICATION_JSON_VALUE);
    res.getWriter().write(json);
    res.getWriter().flush();
  }
}
