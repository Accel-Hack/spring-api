package com.accelhack.application.api.shared.service;

import com.accelhack.application.api.shared.dto.ApiLogDto;
import com.accelhack.application.api.shared.mapper.ApiLogMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApiLogServiceImpl implements ApiLogService {
  private final ApiLogMapper apiLogMapper;

  @Override
  public ApiLogDto add(ApiLogDto apiLogDto) {
    ApiLogDto log = apiLogDto.toCreate();
    apiLogMapper.insert(log);
    return log;
  }
}