package com.accelhack.spring.api.sample.domain.sample;

import com.accelhack.spring.api.sample.domain.Sample;

import java.util.UUID;

public interface SampleRepository {
  Sample findByPk(UUID id);

  void save(Sample sample);

  void delete(UUID id);
}
