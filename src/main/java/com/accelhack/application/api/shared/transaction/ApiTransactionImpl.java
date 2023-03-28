package com.accelhack.application.api.shared.transaction;

import com.accelhack.application.api.shared.dto.ApiLogDto;
import com.accelhack.application.api.shared.service.ApiLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApiTransactionImpl implements ApiTransaction {

  private final ApiLogService apiLogService;

  @Override
  public ApiLogDto save(ApiLogDto apiLogDto) {
    return apiLogService.add(apiLogDto);
  }
}