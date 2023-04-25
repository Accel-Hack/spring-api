package com.accelhack.application.api.shared.model;

import com.accelhack.accelparts.Operand;
import com.accelhack.application.api.shared.dto.SuperuserDto;
import jakarta.validation.constraints.Null;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Superuser implements Operand {

  @Null
  private Integer superuserId;
  private int targetUserId;

  public static Superuser from(SuperuserDto superuserDto) {
    return Superuser.builder().superuserId(superuserDto.getSuperuserId())
        .targetUserId(superuserDto.getTargetUserId()).build();
  }

  public SuperuserDto toSuperuserDto() {
    return SuperuserDto.builder().superuserId(superuserId).targetUserId(targetUserId).build();
  }
}
