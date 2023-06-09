package com.accelhack.spring.api.sample.domain;

import com.accelhack.spring.api.shared.utils.BuilderUtils;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
public class PageableSample {
  @PositiveOrZero
  private final int total;
  @NotNull
  private final List<Sample> samples;

  public static PageableSampleBuilder builder() {
    return new PageableSampleBuilder() {
      @Override
      public PageableSample build() {
        // return domain via validation
        return BuilderUtils.validate(super.build());
      }
    };
  }
}
