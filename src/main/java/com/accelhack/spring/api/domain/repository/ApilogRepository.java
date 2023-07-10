package com.accelhack.spring.api.domain.repository;

import java.util.UUID;

import com.accelhack.spring.api.domain.model.log.Apilog;

public interface ApilogRepository {
  Apilog findById(UUID id);

  void save(Apilog apilog);
}
