package com.accelhack.application.api.shared.model;

import com.accelhack.accelparts.Operand;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class UserSelector extends BaseSelector implements Operand {
}
