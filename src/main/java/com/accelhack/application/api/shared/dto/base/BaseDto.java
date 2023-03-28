package com.accelhack.application.api.shared.dto.base;

public interface BaseDto<T> {
  T toCreate();

  T toUpdate();

  T toDelete();
}
