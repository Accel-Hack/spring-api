package com.accelhack.application.api.app.domain;

import lombok.Getter;

import java.util.Collections;
import java.util.List;

@Getter
public class PageableSample {
  private final int total;
  private final List<Sample> samples;

  public PageableSample(int total, List<Sample> samples, int limit, int offset) {
    this.total = total;
    if (offset >= samples.size()) {
      this.samples = Collections.emptyList();
      return;
    }
    this.samples = samples.subList(offset, Math.min(offset + limit, samples.size()));
  }
}
