package com.accelhack.application.api.app.entity;

import com.accelhack.accelparts.Operand;
import com.accelhack.application.api.shared.model.BaseSelector;
import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class SampleSelector extends BaseSelector implements Operand {
  private Integer id;
}
