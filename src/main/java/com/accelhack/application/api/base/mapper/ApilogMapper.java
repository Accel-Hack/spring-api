package com.accelhack.application.api.base.mapper;

import com.accelhack.application.api.base.domain.Apilog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ApilogMapper {
  Apilog select(String id);

  int save(Apilog apiLogDto);
}
