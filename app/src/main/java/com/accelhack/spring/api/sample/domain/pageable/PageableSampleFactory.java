package com.accelhack.spring.api.sample.domain.pageable;

import com.accelhack.spring.api.sample.domain.PageableSample;
import com.accelhack.spring.api.sample.domain.Sample;

import java.util.List;

public interface PageableSampleFactory {
  PageableSample create(Integer total, List<Sample> samples);
}
