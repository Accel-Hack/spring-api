package com.accelhack.spring.api.sample.repository;

import com.accelhack.spring.api.sample.domain.Sample;
import com.accelhack.spring.api.sample.mapper.SampleMapper;
import com.accelhack.spring.api.sample.domain.sample.SampleRepository;
import com.accelhack.spring.api.sample.dto.SampleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

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
