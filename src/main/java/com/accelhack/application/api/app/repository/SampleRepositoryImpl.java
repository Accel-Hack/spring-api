package com.accelhack.application.api.app.repository;

import com.accelhack.application.api.app.domain.Sample;
import com.accelhack.application.api.app.domain.sample.SampleRepository;
import com.accelhack.application.api.app.dto.SampleDto;
import com.accelhack.application.api.app.mapper.SampleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SampleRepositoryImpl implements SampleRepository {

  private final SampleMapper sampleMapper;

  @Override
  public Sample findByPk(UUID id) {
    SampleDto dto = sampleMapper.select(id.toString());
    return dto.toSampleDomain();
  }

  @Override
  public void save(Sample sample) {
    sampleMapper.save(SampleDto.toSave(sample));
  }

  @Override
  public void delete(UUID id) {
    sampleMapper.delete(id.toString());
  }
}
