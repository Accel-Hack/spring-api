package com.accelhack.application.api.sample.dto;

import com.accelhack.application.api.sample.domain.Sample;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

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
