package com.accelhack.spring.api.domain.model.sample;

import com.accelhack.spring.api.domain.model.sample.SampleQuery.SampleQueryBuilder;
import com.accelhack.spring.api.shared.utils.BuilderUtils;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
public class SampleQuery {
  private final String name;
  @Positive
  @Builder.Default
  private final Integer limit = 20;
  @NotNull
  @Builder.Default
  private final Integer offset = 0;

  public static SampleQueryBuilder builder() {
    return new SampleQueryBuilder() {
      @Override
      public SampleQuery build() {
        // return domain via validation
        return BuilderUtils.validate(super.build());
      }
    };
  }
}
