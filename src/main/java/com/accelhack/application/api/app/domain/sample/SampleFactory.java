package com.accelhack.application.api.app.domain.sample;

import com.accelhack.application.api.app.domain.Sample;
import com.accelhack.application.api.app.dto.SampleQuery;

import java.time.Instant;

public interface SampleFactory {
  Sample createFrom(String name, Instant birthday, Boolean isJapanese);

  SampleQuery buildQuery(String name, Integer limit, Integer offset);
}
