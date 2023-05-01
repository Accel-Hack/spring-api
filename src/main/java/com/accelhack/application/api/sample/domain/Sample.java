package com.accelhack.application.api.sample.domain;

import com.accelhack.application.api.shared.config.MyContext;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Instant;
import java.util.*;

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

  public static class SampleBuilder {
    public Sample build() {
      // set default values
      if (Objects.isNull(id))
        id = UUID.randomUUID();
      // return domain via validation
      return validate(new Sample(id, name, birthday, isJapanese));
    }

    public Sample validate(final Sample sample) {
      final Validator validator = MyContext.getBean(Validator.class);
      final Set<ConstraintViolation<Sample>> errors = validator.validate(sample);
      if (!errors.isEmpty()) {
        throw new IllegalArgumentException(errors.toString());
      }
      return sample;
    }
  }
}
