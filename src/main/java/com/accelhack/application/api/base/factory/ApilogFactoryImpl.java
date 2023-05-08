package com.accelhack.application.api.base.factory;

import com.accelhack.application.api.base.domain.Apilog;
import com.accelhack.application.api.base.domain.apilog.ApilogFactory;
import com.accelhack.application.api.shared.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;
import java.util.UUID;
import java.util.stream.Collectors;

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
