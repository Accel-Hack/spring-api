package com.accelhack.application.api.base.mapper;

import com.accelhack.application.api.base.dto.ApilogDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.UUID;

@Mapper
public interface ApilogMapper {
  ApilogDto select(UUID id);

  int save(ApilogDto apiLogDto);
}
