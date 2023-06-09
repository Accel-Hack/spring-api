package com.accelhack.spring.api.base.log.usecase.impl;

import com.accelhack.spring.api.base.log.domain.Apilog;
import com.accelhack.spring.api.base.log.domain.apilog.ApilogFactory;
import com.accelhack.spring.api.base.log.domain.apilog.ApilogRepository;
import com.accelhack.spring.api.base.log.usecase.ApilogUsecase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

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
      log.info(String.format("[BEGIN(session-%s)] %-4s %s", request.getSession().getId(),
          request.getMethod(), request.getRequestURI() + request.getQueryString()));

      Apilog apilog = apilogFactory.create(request);
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
      Apilog apilog = Optional.ofNullable(apilogRepository.findById(apilogId)).orElseThrow()
          .finished(response, exception);
      apilogRepository.save(apilog);

      log.info(String.format("[END  (session-%s)] took %sms.", apilog.getSessionId(),
          apilog.getExecutionDurationMs()));
    } catch (Throwable t) {
      log.info("ApilogUsecaseImpl.updateResult", t);
    }
  }


}
