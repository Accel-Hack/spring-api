package com.accelhack.spring.api.base.log.usecase;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.UUID;

public interface ApilogUsecase {
  UUID addLog(HttpServletRequest request);

  void updateResult(UUID apilogId, HttpServletResponse response, Exception exception);
}
