package com.accelhack.application.api.shared.model;

import com.accelhack.accelparts.Operand;
import com.accelhack.application.api.shared.dto.PolicyDto;
import com.accelhack.application.api.shared.enums.Actor;
import com.accelhack.application.api.shared.enums.PolicyService;
import lombok.*;

import javax.validation.constraints.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Policy implements Operand {

  //グループを作成
  public interface CreateData {
  }

  public interface RemoveData {
  }

  @Positive(groups = RemoveData.class)
  private int id;
  @NotNull(groups = CreateData.class)
  private Actor actor;
  @NotNull(groups = CreateData.class)
  private PolicyService service;
  @NotBlank(groups = CreateData.class)
  private String action;

  public static Policy from(PolicyDto policyDto) {
    return Policy.builder()
      .actor(policyDto.getActor())
      .service(policyDto.getService())
      .action(policyDto.getAction())
      .build();
  }

  public PolicyDto toPolicyDto() {
    return PolicyDto.builder()
      .id(id)
      .actor(actor)
      .service(service)
      .action(action)
      .build();
  }
}
