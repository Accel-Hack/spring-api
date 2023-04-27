package com.accelhack.application.api.shared.interceptor;

import com.accelhack.application.api.base.usecase.ApilogUsecase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class LoggerInterceptor implements HandlerInterceptor {

  private final ApilogUsecase apilogUsecase;
  private UUID apilogId;

  @Override
  public boolean preHandle(@NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {
    apilogId = apilogUsecase.addLog(request);
    return HandlerInterceptor.super.preHandle(request, response, handler);
  }

  @Override
  public void afterCompletion(@NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response, @NonNull Object handler, Exception ex)
      throws Exception {
    HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    apilogUsecase.updateResult(apilogId, response, ex);
  }
}
