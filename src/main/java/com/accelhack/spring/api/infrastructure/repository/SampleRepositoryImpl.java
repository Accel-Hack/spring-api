package com.accelhack.spring.api.infrastructure.repository;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.accelhack.spring.api.domain.model.sample.Sample;
import com.accelhack.spring.api.domain.repository.SampleRepository;
import com.accelhack.spring.api.infrastructure.dto.SampleDto;
import com.accelhack.spring.api.infrastructure.mapper.SampleMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SampleRepositoryImpl implements SampleRepository {

  private final SampleMapper sampleMapper;

  @Override
  public Sample findByPk(UUID id) {
    SampleDto dto = sampleMapper.select(id);
    return dto.toSampleDomain();
  }

  @Override
  public void save(Sample sample) {
    sampleMapper.save(SampleDto.from(sample));
  }

  @Override
  public void delete(UUID id) {
    sampleMapper.delete(id);
  }
}
