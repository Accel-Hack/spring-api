package com.accelhack.spring.api.application.usecase;

import java.util.UUID;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface ApilogUsecase {
  UUID addLog(HttpServletRequest request);

  void updateResult(UUID apilogId, HttpServletResponse response, Exception exception);
}
