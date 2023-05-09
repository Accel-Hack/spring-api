package com.accelhack.spring.api.base.log.domain.apilog;

import com.accelhack.spring.api.base.log.domain.Apilog;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public interface ApilogFactory {
  Apilog create(HttpServletRequest request) throws IOException;
}
