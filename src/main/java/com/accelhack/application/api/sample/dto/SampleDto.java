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

  public static SampleDto toSave(Sample sample) {
    SampleDto sampleDto = new SampleDto();
    sampleDto.id = sample.getId();
    sampleDto.name = sample.getName();
    sampleDto.birthday = sample.getBirthday();
    sampleDto.isJapanese = sample.getIsJapanese();
    return sampleDto;
  }

  public Sample toSampleDomain() {
    Sample sample = new Sample();
    sample.setId(id);
    sample.setName(name);
    sample.setBirthday(birthday);
    sample.setIsJapanese(isJapanese);
    return sample;
  }
}
