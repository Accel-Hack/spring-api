package com.accelhack.application.api.shared.dto;

import com.accelhack.application.api.shared.dto.base.BaseDto;
import com.accelhack.application.api.shared.enums.Actor;
import com.accelhack.application.api.shared.exception.NotImplementedException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RoleDto implements BaseDto<RoleDto> {
  private int id;
  private Actor actor;
  private String name;
  @JsonIgnore
  private Integer total;

  @Override
  public RoleDto toCreate() {
    return RoleDto.builder()
      .actor(actor)
      .name(name)
      .build();
  }

  @Override
  public RoleDto toUpdate() {
    throw new NotImplementedException();
  }

  @Override
  public RoleDto toDelete() {
    return RoleDto.builder()
      .id(id)
      .build();
  }
}
