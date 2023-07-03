package com.accelhack.spring.api.domain.factory;

import java.time.Instant;

import com.accelhack.spring.api.domain.model.sample.Sample;
import com.accelhack.spring.api.domain.model.sample.SampleQuery;

public interface SampleFactory {
  Sample createFrom(String name, Instant birthday, Boolean isJapanese);

  SampleQuery buildQuery(String name, Integer limit, Integer offset);
}
