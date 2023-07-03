package com.accelhack.spring.api.domain.factory;

import java.io.IOException;
import java.time.Instant;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.accelhack.spring.api.domain.model.log.Apilog;
import com.accelhack.spring.api.domain.service.AuthService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ApilogFactoryImpl implements ApilogFactory {

  private final AuthService service;

  @Override
  public Apilog create(HttpServletRequest request) throws IOException {
    return Apilog.builder().id(UUID.randomUUID()).operator(service.getOperator())
        .sessionId(request.getSession().getId()).remoteAddress(getRemoteAddr(request))
        .operationTime(Instant.now()).method(request.getMethod()).path(request.getRequestURI())
        .query(request.getQueryString())
        .body(request.getReader().lines().collect(Collectors.joining())).build();
  }

  private String getRemoteAddr(HttpServletRequest request) {
    String xForwardedFor = request.getHeader("X-Forwarded-For");
    // ELB等を経由していたらxForwardedForを返す
    if (xForwardedFor != null)
      return xForwardedFor;
    return request.getRemoteAddr();
  }
}
