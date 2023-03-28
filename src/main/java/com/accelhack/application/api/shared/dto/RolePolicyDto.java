package com.accelhack.application.api.shared.dto;

import com.accelhack.application.api.shared.dto.base.BaseDto;
import com.accelhack.application.api.shared.exception.NotImplementedException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RolePolicyDto implements BaseDto<RolePolicyDto> {
  private int id;
  private Integer roleId;
  private Integer policyId;
  @JsonIgnore
  private Integer total;

  @Override
  public RolePolicyDto toCreate() {
    return RolePolicyDto.builder()
      .roleId(roleId)
      .policyId(policyId)
      .build();
  }

  @Override
  public RolePolicyDto toUpdate() {
    throw new NotImplementedException();
  }

  @Override
  public RolePolicyDto toDelete() {
    assert roleId > 0 || policyId > 0;
    return RolePolicyDto.builder()
      .roleId(roleId)
      .policyId(policyId)
      .build();
  }
}
