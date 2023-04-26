package com.accelhack.application.api.app.dto;

import com.accelhack.application.api.app.domain.Sample;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
public class SampleDto {
  private String id;
  private String name;
  private Instant birthday;
  private Boolean isJapanese;

  public static SampleDto toSave(Sample sample) {
    return SampleDto.builder().id(sample.getId().toString()).name(sample.getName())
        .birthday(sample.getBirthday()).isJapanese(sample.getIsJapanese()).build();
  }

  public Sample toSampleDomain() {
    Sample sample = new Sample();
    sample.setId(UUID.fromString(id));
    sample.setName(name);
    sample.setBirthday(birthday);
    sample.setIsJapanese(isJapanese);
    return sample;
  }
}
