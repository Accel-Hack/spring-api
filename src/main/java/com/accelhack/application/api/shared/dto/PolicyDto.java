package com.accelhack.application.api.shared.dto;

import com.accelhack.application.api.shared.dto.base.BaseDto;
import com.accelhack.application.api.shared.enums.Actor;
import com.accelhack.application.api.shared.enums.PolicyService;
import com.accelhack.application.api.shared.exception.NotImplementedException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PolicyDto implements BaseDto<PolicyDto> {
  private int id;
  private Actor actor;
  private PolicyService service;
  private String action;
  @JsonIgnore
  private Integer total;

  @Override
  public PolicyDto toCreate() {
    return PolicyDto.builder()
      .actor(actor)
      .service(service)
      .action(action)
      .build();
  }

  @Override
  public PolicyDto toUpdate() {
    throw new NotImplementedException();
  }

  @Override
  public PolicyDto toDelete() {
    assert id > 0;
    return PolicyDto.builder()
      .id(id)
      .build();
  }
}
