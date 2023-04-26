package com.accelhack.application.api.shared.model;

import com.accelhack.application.api.http.Operand;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserSelector implements Operand {
  private Integer limit = 20;
  private Integer offset = 0;
}
