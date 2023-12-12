package com.accelhack.spring.api.sample.domain.sample;

import com.accelhack.spring.api.sample.domain.PageableSample;
import com.accelhack.spring.api.sample.domain.SampleQuery;

public interface SampleQueryRepository {
  PageableSample search(SampleQuery query);
}
