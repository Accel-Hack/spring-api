package com.accelhack.spring.api.domain.factory;

import java.util.List;

import org.springframework.stereotype.Component;

import com.accelhack.spring.api.domain.model.sample.PageableSample;
import com.accelhack.spring.api.domain.model.sample.Sample;

@Component
public class PageableSampleFactoryImpl implements PageableSampleFactory {
  @Override
  public PageableSample create(Integer total, List<Sample> samples) {
    return PageableSample.builder().total(total).samples(samples).build();
  }
}
