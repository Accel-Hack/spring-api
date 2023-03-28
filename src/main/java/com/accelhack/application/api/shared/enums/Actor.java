package com.accelhack.application.api.shared.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Getter
@AllArgsConstructor
public enum Actor implements NumericEnum<Actor> {
  SYSTEM(1, "SYSTEM"), // システム管理者
  MANAGER(2, "MANAGER"), // 管理者
  USER(3, "USER"); // エンドユーザ

  private final int code;
  private final String role;

  public SimpleGrantedAuthority toAuthority() {
    return new SimpleGrantedAuthority(String.format("ROLE_%s", role));
  }
}
