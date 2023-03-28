package com.accelhack.application.api.shared.dto;

import com.accelhack.application.api.shared.dto.base.BaseDto;
import com.accelhack.application.api.shared.exception.NotImplementedException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserRoleDto implements BaseDto<UserRoleDto> {
  private int id;
  private Integer userId;
  private Integer roleId;
  @JsonIgnore
  private Integer total;

  @Override
  public UserRoleDto toCreate() {
    assert userId > 0 && roleId > 0;
    return UserRoleDto.builder()
      .userId(userId)
      .roleId(roleId)
      .build();
  }

  @Override
  public UserRoleDto toUpdate() {
    throw new NotImplementedException();
  }

  @Override
  public UserRoleDto toDelete() {
    assert userId > 0 || roleId > 0;
    return UserRoleDto.builder()
      .userId(userId)
      .roleId(roleId)
      .build();
  }
}
