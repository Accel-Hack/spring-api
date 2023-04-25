package com.accelhack.application.api.shared.model;

import com.accelhack.accelparts.Operand;
import com.accelhack.application.api.shared.dto.UserDto;
import com.accelhack.application.api.shared.enums.Actor;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class User implements Operand {

  private Integer id;
  private String username;
  private Actor actor;

  public static User from(UserDto userDto) {
    return User.builder().id(userDto.getId()).username(userDto.getUsername())
        .actor(userDto.getActor()).build();
  }
}
