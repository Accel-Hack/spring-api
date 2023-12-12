package com.accelhack.spring.api.base.log.mapper;

import com.accelhack.spring.api.base.log.dto.ApilogDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.UUID;

@Mapper
public interface ApilogMapper {
  ApilogDto select(UUID id);

  int save(ApilogDto apiLogDto);
}
