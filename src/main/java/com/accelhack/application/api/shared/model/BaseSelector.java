package com.accelhack.application.api.shared.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public abstract class BaseSelector {

  @Builder.Default
  private Integer limit = 20;
  private Integer offset = 0;
}
