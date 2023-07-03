package com.accelhack.spring.api.domain.factory;

import java.time.Instant;
import java.util.UUID;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.accelhack.spring.api.domain.model.sample.Sample;
import com.accelhack.spring.api.domain.model.sample.SampleQuery;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SampleFactoryImpl implements SampleFactory {
  public Sample createFrom(String name, Instant birthday, Boolean isJapanese) {
    return Sample.builder().id(UUID.randomUUID()).name(name).birthday(birthday)
        .isJapanese(isJapanese).build();
  }

  @Override
  public SampleQuery buildQuery(String name, Integer limit, Integer offset) {
    return SampleQuery.builder().name(name).limit(limit).offset(offset).build();
  }
}
