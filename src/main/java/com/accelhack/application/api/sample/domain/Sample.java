package com.accelhack.application.api.sample.domain;

import com.accelhack.application.api.shared.utils.BuilderUtils;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

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
    return new CustomSampleBuilder();
  }

  public static class CustomSampleBuilder extends SampleBuilder {
    @Override
    public Sample build() {
      // return domain via validation
      return BuilderUtils.validate(super.build());
    }
  }
}
