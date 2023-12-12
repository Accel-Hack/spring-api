package com.accelhack.spring.api.base.log.domain.apilog;

import com.accelhack.spring.api.base.log.domain.Apilog;

import java.util.UUID;

public interface ApilogRepository {
  Apilog findById(UUID id);

  void save(Apilog apilog);
}
