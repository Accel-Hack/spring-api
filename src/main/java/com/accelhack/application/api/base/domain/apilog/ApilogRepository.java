package com.accelhack.application.api.base.domain.apilog;

import com.accelhack.application.api.base.domain.Apilog;

import java.util.UUID;

public interface ApilogRepository {
  Apilog findById(UUID id);

  void save(Apilog apilog);
}
