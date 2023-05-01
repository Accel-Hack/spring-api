package com.accelhack.application.api.base.dto;

import com.accelhack.application.api.base.domain.User;
import com.accelhack.application.api.base.enums.Actor;
import lombok.Getter;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Getter
public class UserDto {

  private UUID id;
  private String username;
  private String encryptPassword;
  private Actor actor;
  private String resetCode;
  private Instant resetUntil;
  private List<TokenDto> tokens;

  public static UserDto from(User user) {
    UserDto userDto = new UserDto();
    userDto.id = user.getId();
    userDto.username = user.getUsername();
    userDto.encryptPassword = user.getEncryptPassword();
    userDto.actor = user.getActor();
    userDto.resetCode = user.getResetCode();
    userDto.resetUntil = user.getResetUntil();
    userDto.tokens = user.getTokens().stream().map(TokenDto::from).toList();
    return userDto;
  }

  public User toUserDomain() {
    return User.builder().id(id).username(username).encryptPassword(encryptPassword).actor(actor)
        .resetCode(resetCode).resetUntil(resetUntil)
        .tokens(tokens.stream().map(TokenDto::toTokenDomain).toList()).build();
  }

  @Getter
  static public class TokenDto {
    private UUID id;
    private String accessToken;
    private String encryptRefreshToken;
    private Instant expiresAt;

    public static TokenDto from(User.Token token) {
      TokenDto tokenDto = new TokenDto();
      tokenDto.id = token.getId();
      tokenDto.accessToken = token.getAccessToken();
      tokenDto.encryptRefreshToken = token.getEncryptRefreshToken();
      tokenDto.expiresAt = token.getExpiresAt();
      return tokenDto;
    }

    public User.Token toTokenDomain() {
      return User.Token.builder().id(id).accessToken(accessToken)
          .encryptRefreshToken(encryptRefreshToken).expiresAt(expiresAt).build();
    }
  }
}
