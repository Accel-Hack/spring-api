package com.accelhack.application.api.shared.mapper;

import com.accelhack.application.api.shared.domain.log.Log;
import com.accelhack.application.api.shared.dto.ApiLogDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ApiLogMapper {
  int insert(ApiLogDto apiLogDto);

  int insert(Log log);
}
