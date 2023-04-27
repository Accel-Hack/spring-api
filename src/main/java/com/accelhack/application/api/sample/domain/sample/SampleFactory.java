package com.accelhack.application.api.sample.domain.sample;

import com.accelhack.application.api.sample.domain.Sample;
import com.accelhack.application.api.sample.dto.SampleQuery;

import java.time.Instant;

public interface SampleFactory {
  Sample createFrom(String name, Instant birthday, Boolean isJapanese);

  SampleQuery buildQuery(String name, Integer limit, Integer offset);
}
