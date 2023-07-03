package com.accelhack.spring.api.infrastructure.dto;

import java.util.List;

import com.accelhack.spring.api.domain.model.sample.Sample;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SampleQueryDto {
  private Integer total;
  private List<SampleDto> sampleDtos;

  public List<Sample> getSamples() {
    return sampleDtos.stream().map(SampleDto::toSampleDomain).toList();
  }
}
