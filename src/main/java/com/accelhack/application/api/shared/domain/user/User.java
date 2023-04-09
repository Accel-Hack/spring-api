package com.accelhack.application.api.shared.domain.user;

import com.accelhack.application.api.shared.enums.Actor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

public class User {
  private int id;

  /**
   * sign-in id
   */
  private String username;

  /**
   * encrypt password
   */
  private String password;

  /**
   * user role
   */
  private Actor actor;
  private String resetCode;
  private Instant resetUntil;

  private List<Token> tokens;

  static public class Token {
    private int id;
    private String accessToken;
    private String refreshToken;
    private Instant expiresAt;
  }

  private User(String username, String password, Actor actor) {
    this.username = username;
    this.password = password;
    this.actor = actor;
  }

  @RequiredArgsConstructor
  @Component
  static public class CreateBuilder {
    private final BCryptPasswordEncoder passwordEncoder;

    public User create(String username, String password, Actor actor) {
      return new User(username, passwordEncoder.encode(password), actor);
    }
  }
}
