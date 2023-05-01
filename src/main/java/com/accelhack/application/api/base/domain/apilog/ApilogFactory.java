package com.accelhack.application.api.base.domain.apilog;

import com.accelhack.application.api.base.domain.Apilog;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public interface ApilogFactory {
  Apilog create(HttpServletRequest request) throws IOException;
}
