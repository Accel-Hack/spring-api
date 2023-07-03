package com.accelhack.spring.api.domain.factory;

import java.io.IOException;

import com.accelhack.spring.api.domain.model.log.Apilog;

import jakarta.servlet.http.HttpServletRequest;

public interface ApilogFactory {
  Apilog create(HttpServletRequest request) throws IOException;
}
