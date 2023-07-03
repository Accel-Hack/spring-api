package com.accelhack.spring.api.domain.model.sample;

import java.time.Instant;
import java.util.UUID;

import com.accelhack.spring.api.domain.model.sample.Sample.SampleBuilder;
import com.accelhack.spring.api.shared.utils.BuilderUtils;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
public class Sample {
  @NotNull
  private final UUID id;
  @NotNull
  private final String name;
  @NotNull
  private final Instant birthday;
  @NotNull
  private final Boolean isJapanese;

  public static SampleBuilder builder() {
    return new SampleBuilder() {
      @Override
      public Sample build() {
        // return domain via validation
        return BuilderUtils.validate(super.build());
      }
    };
  }
}
