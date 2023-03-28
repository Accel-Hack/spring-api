package com.accelhack.application.api.app.mapper;

import com.accelhack.application.api.app.dto.SampleDto;
import com.accelhack.application.api.app.entity.SampleSelector;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SampleMapper {
  SampleDto select(int id);

  List<SampleDto> selectBy(SampleSelector sampleSelector);

  int insert(SampleDto sampleDto);

  int update(SampleDto sampleDto);

  int delete(SampleDto sampleDto);
}
