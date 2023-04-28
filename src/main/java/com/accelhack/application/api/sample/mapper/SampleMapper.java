package com.accelhack.application.api.sample.mapper;

import com.accelhack.application.api.sample.domain.SampleQuery;
import com.accelhack.application.api.sample.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.UUID;

@Mapper
public interface SampleMapper {
  SampleDto select(UUID id);

  SampleQueryDto searchBy(SampleQuery query);

  void save(SampleDto sampleDto);

  void delete(UUID id);
}
