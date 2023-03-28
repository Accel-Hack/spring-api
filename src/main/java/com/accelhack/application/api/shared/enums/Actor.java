package com.accelhack.application.api.shared.enums;

import com.accelhack.application.api.shared.controller.UIController;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Getter
@AllArgsConstructor
public enum Actor implements NumericEnum<Actor> {
  SYSTEM(1, UIController.SYSTEM_CONTEXT, "SYSTEM"), // システム管理者
  MANAGER(2, UIController.MANAGER_CONTEXT, "MANAGER"), // 管理者
  USER(3, UIController.USER_CONTEXT, "USER"); // エンドユーザ

  private final int code;
  private final String path;
  private final String role;

  public SimpleGrantedAuthority toAuthority() {
    return new SimpleGrantedAuthority(String.format("ROLE_%s", role));
  }
}
