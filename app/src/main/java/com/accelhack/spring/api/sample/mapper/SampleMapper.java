package com.accelhack.spring.api.sample.mapper;

import com.accelhack.spring.api.sample.domain.SampleQuery;
import com.accelhack.spring.api.sample.dto.SampleDto;
import com.accelhack.spring.api.sample.dto.SampleQueryDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.UUID;

@Mapper
public interface SampleMapper {
  SampleDto select(UUID id);

  SampleQueryDto searchBy(SampleQuery query);

  void save(SampleDto sampleDto);

  void delete(UUID id);
}
