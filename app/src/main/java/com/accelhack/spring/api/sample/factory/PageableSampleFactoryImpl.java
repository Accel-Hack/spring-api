package com.accelhack.spring.api.sample.factory;

import com.accelhack.spring.api.sample.domain.PageableSample;
import com.accelhack.spring.api.sample.domain.Sample;
import com.accelhack.spring.api.sample.domain.pageable.PageableSampleFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PageableSampleFactoryImpl implements PageableSampleFactory {
  @Override
  public PageableSample create(Integer total, List<Sample> samples) {
    return PageableSample.builder().total(total).samples(samples).build();
  }
}
