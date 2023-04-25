package com.accelhack.application.api.shared.transaction;

import com.accelhack.application.api.shared.dto.ApiLogDto;
import org.springframework.transaction.annotation.Transactional;

public interface ApiTransaction {
  @Transactional(rollbackFor = Throwable.class)
  ApiLogDto save(ApiLogDto apiLogDto);
}
