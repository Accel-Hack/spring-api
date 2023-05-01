package com.accelhack.application.api.base.repository;

import com.accelhack.application.api.base.domain.Apilog;
import com.accelhack.application.api.base.domain.apilog.ApilogRepository;
import com.accelhack.application.api.base.dto.ApilogDto;
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
    return apilogMapper.select(id).toApilogDomain();
  }

  @Override
  public void save(Apilog apilog) {
    apilogMapper.save(ApilogDto.from(apilog));
  }
}
