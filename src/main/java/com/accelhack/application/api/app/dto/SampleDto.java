package com.accelhack.application.api.app.dto;

import com.accelhack.application.api.shared.dto.base.BaseDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class SampleDto implements BaseDto<SampleDto> {
  private Integer id;
  private String name;
  private Instant birthday;
  private Boolean isJapanese;

  @JsonIgnore
  private Integer total;

  @Override
  public SampleDto toCreate() {
    return SampleDto.builder()
      .name(name)
      .birthday(birthday)
      .isJapanese(isJapanese)
      .build();
  }

  @Override
  public SampleDto toUpdate() {
    return SampleDto.builder()
      .id(id)
      .name(name)
      .birthday(birthday)
      .isJapanese(isJapanese)
      .build();
  }

  @Override
  public SampleDto toDelete() {
    return SampleDto.builder()
      .id(id)
      .build();
  }
}
