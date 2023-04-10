package com.accelhack.application.api.shared.mapper;

import com.accelhack.application.api.shared.domain.log.Log;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogMapper {
  int insert(Log log);
}
