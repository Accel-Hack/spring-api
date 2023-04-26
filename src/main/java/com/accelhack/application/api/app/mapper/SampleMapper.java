package com.accelhack.application.api.app.mapper;

import com.accelhack.application.api.app.dto.SampleQuery;
import com.accelhack.application.api.app.dto.SampleDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SampleMapper {
  SampleDto select(String id);

  List<SampleDto> searchBy(SampleQuery query);

  int save(SampleDto sampleDto);

  int delete(String id);
}
