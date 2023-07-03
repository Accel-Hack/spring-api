package com.accelhack.spring.api.infrastructure.mapper;

import java.util.UUID;

import org.apache.ibatis.annotations.Mapper;

import com.accelhack.spring.api.domain.model.sample.SampleQuery;
import com.accelhack.spring.api.infrastructure.dto.SampleDto;
import com.accelhack.spring.api.infrastructure.dto.SampleQueryDto;

@Mapper
public interface SampleMapper {
  SampleDto select(UUID id);

  SampleQueryDto searchBy(SampleQuery query);

  void save(SampleDto sampleDto);

  void delete(UUID id);
}
