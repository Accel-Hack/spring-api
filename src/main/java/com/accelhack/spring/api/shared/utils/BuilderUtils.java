package com.accelhack.spring.api.shared.utils;

import com.accelhack.spring.api.shared.config.MyContext;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

import java.util.Set;

public class BuilderUtils {
  public static <T> T validate(final T t) {
    final Validator validator = MyContext.getBean(Validator.class);
    final Set<ConstraintViolation<T>> errors = validator.validate(t);
    if (!errors.isEmpty()) {
      throw new IllegalArgumentException(errors.toString());
    }
    return t;
  }
}
