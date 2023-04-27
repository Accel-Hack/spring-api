package com.accelhack.application.api.base.repository;

import com.accelhack.application.api.base.domain.Apilog;
import com.accelhack.application.api.base.domain.apilog.ApilogRepository;
import com.accelhack.application.api.base.mapper.ApilogMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ApilogRepositoryImpl implements ApilogRepository {
  private final ApilogMapper apilogMapper;

  @Override
  public Apilog findById(UUID id) {
    return apilogMapper.select(id.toString());
  }

  @Override
  public void save(Apilog apilog) {
    apilogMapper.save(apilog);
  }
}
