package com.accelhack.application.api.sample.domain;

import com.accelhack.application.api.shared.config.MyContext;
import jakarta.validation.Validator;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.Optional;
import java.util.Set;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
public class SampleQuery {
  private final String name;
  @Positive
  private final int limit;
  @NotNull
  private final int offset;

  public static class SampleQueryBuilder {

    public SampleQueryBuilder limit(final Integer limit) {
      this.limit = Optional.ofNullable(limit).orElse(20);
      return this;
    }

    public SampleQueryBuilder offset(final Integer offset) {
      this.offset = Optional.ofNullable(offset).orElse(0);
      return this;
    }

    public SampleQuery build() {
      return validate(new SampleQuery(name, limit, offset));
    }

    public SampleQuery validate(final SampleQuery sampleQuery) {
      final Validator validator = MyContext.getBean(Validator.class);
      final Set<?> errors = validator.validate(sampleQuery);
      if (!errors.isEmpty()) {
        throw new IllegalArgumentException(errors.toString());
      }
      return sampleQuery;
    }
  }
}
