package com.accelhack.spring.api.domain.factory;

import java.util.List;

import com.accelhack.spring.api.domain.model.sample.PageableSample;
import com.accelhack.spring.api.domain.model.sample.Sample;

public interface PageableSampleFactory {
  PageableSample create(Integer total, List<Sample> samples);
}
