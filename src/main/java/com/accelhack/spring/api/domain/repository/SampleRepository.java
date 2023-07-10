package com.accelhack.spring.api.domain.repository;

import java.util.UUID;

import com.accelhack.spring.api.domain.model.sample.Sample;

public interface SampleRepository {
  Sample findByPk(UUID id);

  void save(Sample sample);

  void delete(UUID id);
}
