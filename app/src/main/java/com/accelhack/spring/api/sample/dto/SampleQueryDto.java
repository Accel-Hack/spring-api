package com.accelhack.spring.api.sample.dto;

import com.accelhack.spring.api.sample.domain.Sample;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class SampleQueryDto {
  private Integer total;
  private List<SampleDto> sampleDtos;

  public List<Sample> getSamples() {
    return sampleDtos.stream().map(SampleDto::toSampleDomain).toList();
  }
}
