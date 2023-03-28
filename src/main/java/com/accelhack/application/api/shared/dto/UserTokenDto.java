package com.accelhack.application.api.shared.dto;

import com.accelhack.application.api.shared.dto.base.BaseDto;
import com.accelhack.application.api.shared.exception.NotImplementedException;
import lombok.*;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserTokenDto implements BaseDto<UserTokenDto> {

  private int id;
  private int userId;
  private String accessToken;
  private String refreshToken;
  private Instant expiresAt;

  @Override
  public UserTokenDto toCreate() {
    return UserTokenDto.builder()
      .userId(userId)
      .accessToken(accessToken)
      .refreshToken(refreshToken)
      .expiresAt(expiresAt)
      .build();
  }

  @Override
  public UserTokenDto toUpdate() {
    throw new NotImplementedException();
  }

  @Override
  public UserTokenDto toDelete() {
    return UserTokenDto.builder()
      .refreshToken(refreshToken)
      .build();
  }
}
