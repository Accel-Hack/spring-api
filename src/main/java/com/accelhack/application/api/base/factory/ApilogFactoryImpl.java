package com.accelhack.application.api.base.factory;

import com.accelhack.application.api.base.domain.Apilog;
import com.accelhack.application.api.base.domain.apilog.ApilogFactory;
import com.accelhack.application.api.shared.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ApilogFactoryImpl implements ApilogFactory {

  private final AuthService service;

  @Override
  public Apilog create(String sessionId, String remoteAddress, String path, String method,
      String query, String body) {
    Apilog apilog = new Apilog();
    apilog.setId(UUID.randomUUID());
    apilog.setOperator(service.getOperator());
    apilog.setSessionId(sessionId);
    apilog.setRemoteAddress(remoteAddress);
    apilog.setOperationTime(Instant.now());
    apilog.setPath(path);
    apilog.setMethod(method);
    apilog.setQuery(query);
    apilog.setBody(body);
    return apilog;
  }
}
