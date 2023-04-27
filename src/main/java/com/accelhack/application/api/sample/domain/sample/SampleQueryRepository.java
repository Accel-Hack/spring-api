package com.accelhack.application.api.sample.domain.sample;

import com.accelhack.application.api.sample.domain.PageableSample;
import com.accelhack.application.api.sample.dto.SampleQuery;

public interface SampleQueryRepository {
  PageableSample search(SampleQuery query);
}
