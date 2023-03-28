package com.accelhack.application.api.shared.dto;

import com.accelhack.application.api.shared.dto.base.BaseDto;
import com.accelhack.application.api.shared.enums.Actor;
import com.accelhack.application.api.shared.exception.NotImplementedException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDto implements BaseDto<UserDto> {

  private int id;
  private String username;
  private String password;
  private Actor actor;
  private String resetCode;
  private Instant resetUntil;

  @JsonIgnore
  private Integer total;

  @Override
  public UserDto toCreate() {
    return UserDto.builder()
      .username(username)
      .password(password)
      .actor(actor)
      .build();
  }

  @Override
  public UserDto toUpdate() {
    throw new NotImplementedException();
  }

  @Override
  public UserDto toDelete() {
    throw new NotImplementedException();
  }

  @RequiredArgsConstructor
  @Component
  static class CreateBuilder {
    private final BCryptPasswordEncoder passwordEncoder;

    public UserDto build(String username, String password) {
      return UserDto.builder()
        .username(username)
        .password(passwordEncoder.encode(password))
        .build();
    }
  }
}
