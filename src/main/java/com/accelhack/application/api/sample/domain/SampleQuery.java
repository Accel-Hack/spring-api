package com.accelhack.application.api.sample.domain;

import com.accelhack.application.api.shared.config.MyContext;
import jakarta.validation.Validator;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.Objects;
import java.util.Set;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
public class SampleQuery {
  private final String name;
  @Positive
  private final Integer limit;
  @NotNull
  private final Integer offset;

  public static class SampleQueryBuilder {
    public SampleQuery build() {
      // set default values
      if (Objects.isNull(limit))
        limit = 20;
      if (Objects.isNull(offset))
        offset = 0;
      // return domain via validation
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
