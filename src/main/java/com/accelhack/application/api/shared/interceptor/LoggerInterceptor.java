package com.accelhack.application.api.shared.interceptor;

import com.accelhack.application.api.shared.dto.ApiLogDto;
import com.accelhack.application.api.shared.registry.AuthRegistry;
import com.accelhack.application.api.shared.transaction.ApiTransaction;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.Instant;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.lang.StrictMath.round;

@Component
@RequiredArgsConstructor
@Slf4j
public class LoggerInterceptor implements HandlerInterceptor {

  private Instant begin;

  private final AuthRegistry authRegistry;

  private final ApiTransaction apiTransaction;

  @Override
  public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {
    log.info(String.format("[%-5s(session-%s)] %-4s %s ip:%s", "BEGIN", request.getSession().getId(), request.getMethod(), request.getRequestURI(), getRemoteAddr(request)));
    begin = Instant.now();
    return HandlerInterceptor.super.preHandle(request, response, handler);
  }

  @Override
  public void afterCompletion(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler, Exception ex) throws Exception {
    HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    double durationMs = round((Instant.now().getNano() - begin.getNano()) / 1000.0) / 1000.0;
    addLog(request, response.getStatus(), durationMs, ex);
    log.info(String.format("[%-5s(session-%s)] took %sms.", "END", request.getSession().getId(), durationMs));
  }

  private String getRemoteAddr(HttpServletRequest request) {
    String xForwardedFor = request.getHeader("X-Forwarded-For");
    // ELB等を経由していたらxForwardedForを返す
    if (xForwardedFor != null) return xForwardedFor;
    return request.getRemoteAddr();
  }

  private void addLog(@NonNull HttpServletRequest request, int status, double executeTime, Exception ex) {
    try {
      ApiLogDto apiLog = ApiLogDto.builder()
        .operationTime(begin)
        .operator(authRegistry.getOperator())
        .method(request.getMethod())
        .path(request.getRequestURI())
        .query(request.getQueryString())
        .requestBody(request.getReader().lines().collect(Collectors.joining()))
        .remoteIp(getRemoteAddr(request))
        .executionTimeMs(executeTime)
        .status(status)
        .exception(Objects.nonNull(ex) ? ex.getLocalizedMessage() : "")
        .build();
      apiTransaction.save(apiLog);
    } catch (Throwable t) {
      log.error(t.getLocalizedMessage());
    }
  }
}
