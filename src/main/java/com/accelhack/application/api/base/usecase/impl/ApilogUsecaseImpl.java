package com.accelhack.application.api.base.usecase.impl;

import com.accelhack.application.api.base.domain.Apilog;
import com.accelhack.application.api.base.domain.apilog.ApilogFactory;
import com.accelhack.application.api.base.domain.apilog.ApilogRepository;
import com.accelhack.application.api.base.usecase.ApilogUsecase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class ApilogUsecaseImpl implements ApilogUsecase {

  private final ApilogFactory apilogFactory;
  private final ApilogRepository apilogRepository;

  @Override
  public UUID addLog(HttpServletRequest request) {
    UUID apilogId = null;
    try {
      String sessionId = request.getSession().getId();
      String remoteAddress = getRemoteAddr(request);
      String path = request.getRequestURI();
      String method = request.getMethod();
      String query = request.getQueryString();

      log.info(String.format("[BEGIN(session-%s)] %-4s %s ip:%s", sessionId, method, path,
          remoteAddress));

      String body = request.getReader().lines().collect(Collectors.joining());
      Apilog apilog = apilogFactory.create(sessionId, remoteAddress, path, method, query, body);
      apilogRepository.save(apilog);

      apilogId = apilog.getId();
    } catch (Throwable t) {
      log.info("ApilogUsecaseImpl.addLog", t);
    }
    return apilogId;
  }

  @Override
  public void updateResult(UUID apilogId, HttpServletResponse response, Exception exception) {
    try {
      Apilog apilog = apilogRepository.findById(apilogId);
      if (Objects.isNull(apilog))
        return;

      apilog.finished(response, exception);
      apilogRepository.save(apilog);

      log.info(String.format("[END  (session-%s)] took %sms.", apilog.getSessionId(),
          apilog.getExecutionDurationMs()));
    } catch (Throwable t) {
      log.info("ApilogUsecaseImpl.updateResult", t);
    }
  }

  private String getRemoteAddr(HttpServletRequest request) {
    String xForwardedFor = request.getHeader("X-Forwarded-For");
    // ELB等を経由していたらxForwardedForを返す
    if (xForwardedFor != null)
      return xForwardedFor;
    return request.getRemoteAddr();
  }
}
