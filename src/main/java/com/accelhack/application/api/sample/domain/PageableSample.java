package com.accelhack.application.api.sample.domain;

import com.accelhack.application.api.shared.config.MyContext;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
public class PageableSample {
  @NotNull
  private final int total;
  @NotNull
  private final List<Sample> samples;

  public static class PageableSampleBuilder {

    public PageableSample build() {
      return validate(new PageableSample(total, samples));
    }

    public PageableSample validate(final PageableSample pageableSample) {
      final Validator validator = MyContext.getBean(Validator.class);
      final Set<ConstraintViolation<PageableSample>> errors = validator.validate(pageableSample);
      if (!errors.isEmpty()) {
        throw new IllegalArgumentException(errors.toString());
      }
      return pageableSample;
    }
  }
}
