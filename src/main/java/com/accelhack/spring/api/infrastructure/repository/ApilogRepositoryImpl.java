package com.accelhack.spring.api.infrastructure.repository;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.accelhack.spring.api.domain.model.log.Apilog;
import com.accelhack.spring.api.domain.repository.ApilogRepository;
import com.accelhack.spring.api.infrastructure.dto.ApilogDto;
import com.accelhack.spring.api.infrastructure.mapper.ApilogMapper;

import lombok.RequiredArgsConstructor;

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
