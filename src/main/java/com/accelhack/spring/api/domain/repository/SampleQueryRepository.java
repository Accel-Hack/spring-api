package com.accelhack.spring.api.domain.repository;

import com.accelhack.spring.api.domain.model.sample.PageableSample;
import com.accelhack.spring.api.domain.model.sample.SampleQuery;

public interface SampleQueryRepository {
  PageableSample search(SampleQuery query);
}
