package com.accelhack.application.api.shared.dto;

import com.accelhack.application.api.shared.dto.base.BaseDto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SuperuserDto implements BaseDto<SuperuserDto> {

  private int id;
  private int superuserId;
  private UserDto superuser;
  private int targetUserId;
  private UserDto targetUser;

  @Override
  public SuperuserDto toCreate() {
    return SuperuserDto.builder()
      .superuserId(superuserId)
      .targetUserId(targetUserId)
      .build();
  }

  @Override
  public SuperuserDto toUpdate() {
    return SuperuserDto.builder()
      .id(id)
      .build();
  }

  @Override
  public SuperuserDto toDelete() {
    return SuperuserDto.builder()
      .id(superuserId)
      .build();
  }
}
