package com.accelhack.spring.api.base.log.repository;

import com.accelhack.spring.api.base.log.domain.Apilog;
import com.accelhack.spring.api.base.log.domain.apilog.ApilogRepository;
import com.accelhack.spring.api.base.log.dto.ApilogDto;
import com.accelhack.spring.api.base.log.mapper.ApilogMapper;
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
