package com.accelhack.application.api.sample.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
public class Sample {
  private UUID id;
  private String name;
  private Instant birthday;
  private Boolean isJapanese;

  public void changeName(String name) {
    this.name = name;
  }

  public void changeBirthday(Instant birthday) {
    this.birthday = birthday;
  }

  public void changeIsJapanese(Boolean isJapanese) {
    this.isJapanese = isJapanese;
  }
}
