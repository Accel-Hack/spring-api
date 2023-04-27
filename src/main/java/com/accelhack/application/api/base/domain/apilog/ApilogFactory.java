package com.accelhack.application.api.base.domain.apilog;

import com.accelhack.application.api.base.domain.Apilog;

public interface ApilogFactory {
  Apilog create(String sessionId, String remoteAddress, String path, String method, String query,
      String body);
}
