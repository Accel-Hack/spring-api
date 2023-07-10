package com.accelhack.spring.api.infrastructure.dto;

import java.time.Instant;
import java.util.UUID;

import com.accelhack.spring.api.domain.model.sample.Sample;

import lombok.Getter;

@Getter
public class SampleDto {
  private UUID id;
  private String name;
  private Instant birthday;
  private Boolean isJapanese;

  public static SampleDto from(Sample sample) {
    SampleDto sampleDto = new SampleDto();
    sampleDto.id = sample.getId();
    sampleDto.name = sample.getName();
    sampleDto.birthday = sample.getBirthday();
    sampleDto.isJapanese = sample.getIsJapanese();
    return sampleDto;
  }

  public Sample toSampleDomain() {
    // make sample entity from builder
    return Sample.builder().id(id).name(name).birthday(birthday).isJapanese(isJapanese).build();
  }
}
