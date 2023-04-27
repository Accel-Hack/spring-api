package com.accelhack.application.api.sample.factory;

import com.accelhack.application.api.sample.domain.Sample;
import com.accelhack.application.api.sample.domain.sample.SampleFactory;
import com.accelhack.application.api.sample.dto.SampleQuery;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SampleFactoryImpl implements SampleFactory {
  public Sample createFrom(String name, Instant birthday, Boolean isJapanese) {
    Sample sample = new Sample();
    sample.setId(UUID.randomUUID());
    sample.setName(name);
    sample.setBirthday(birthday);
    sample.setIsJapanese(isJapanese);
    return sample;
  }

  @Override
  public SampleQuery buildQuery(String name, Integer limit, Integer offset) {
    return new SampleQuery(name, Optional.ofNullable(limit).orElse(20),
        Optional.ofNullable(offset).orElse(20));
  }
}
