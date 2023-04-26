package com.accelhack.application.api.app.domain.sample;

import com.accelhack.application.api.app.domain.Sample;

import java.util.UUID;

public interface SampleRepository {
  Sample findByPk(UUID id);

  void save(Sample sample);

  void delete(UUID id);
}
