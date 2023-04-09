package com.accelhack.application.api.app.entity;

import com.accelhack.accelparts.Operand;
import com.accelhack.application.api.app.dto.SampleDto;
import lombok.*;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Sample implements Operand {

  private Integer id;
  private String name;
  private Instant birthday;
  private Boolean isJapanese;

  public static Sample from(SampleDto sampleDto) {
    return Sample.builder()
      .id(sampleDto.getId())
      .name(sampleDto.getName())
      .birthday(sampleDto.getBirthday())
      .isJapanese(sampleDto.getIsJapanese())
      .build();
  }

  public SampleDto toSampleDto() {
    return SampleDto.builder()
      .id(id)
      .name(name)
      .birthday(birthday)
      .isJapanese(isJapanese)
      .build();
  }
}
