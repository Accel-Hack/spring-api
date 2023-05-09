package com.accelhack.spring.api.shared.enums;

import java.util.Arrays;
import java.util.function.Predicate;

public interface NumericEnum<E extends NumericEnum<E>> {
  static <E extends Enum<E> & NumericEnum<E>> NumericEnum<E> getByCode(Class<E> enumClass,
      Predicate<E> p) {
    return Arrays.stream(enumClass.getEnumConstants()).filter(p).findFirst().orElse(null);
  }

  int getCode();
}
