package com.accelhack.application.api.sample.factory;

import com.accelhack.application.api.sample.domain.PageableSample;
import com.accelhack.application.api.sample.domain.Sample;
import com.accelhack.application.api.sample.domain.pageable.PageableSampleFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PageableSampleFactoryImpl implements PageableSampleFactory {
  @Override
  public PageableSample create(Integer total, List<Sample> samples) {
    return PageableSample.builder().total(total).samples(samples).build();
  }
}
