package com.accelhack.spring.api.infrastructure.mapper;

import java.util.UUID;

import org.apache.ibatis.annotations.Mapper;

import com.accelhack.spring.api.infrastructure.dto.ApilogDto;

@Mapper
public interface ApilogMapper {
  ApilogDto select(UUID id);

  int save(ApilogDto apiLogDto);
}
