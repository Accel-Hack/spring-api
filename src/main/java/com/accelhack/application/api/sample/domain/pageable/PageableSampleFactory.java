package com.accelhack.application.api.sample.domain.pageable;

import com.accelhack.application.api.sample.domain.PageableSample;
import com.accelhack.application.api.sample.domain.Sample;

import java.util.List;

public interface PageableSampleFactory {
  PageableSample create(Integer total, List<Sample> samples);
}
