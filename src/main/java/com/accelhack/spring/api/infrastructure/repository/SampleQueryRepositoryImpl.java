package com.accelhack.spring.api.infrastructure.repository;

import java.util.Collections;
import java.util.Objects;

import org.springframework.stereotype.Component;

import com.accelhack.spring.api.domain.factory.PageableSampleFactory;
import com.accelhack.spring.api.domain.model.sample.PageableSample;
import com.accelhack.spring.api.domain.model.sample.SampleQuery;
import com.accelhack.spring.api.domain.repository.SampleQueryRepository;
import com.accelhack.spring.api.infrastructure.dto.SampleQueryDto;
import com.accelhack.spring.api.infrastructure.mapper.SampleMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SampleQueryRepositoryImpl implements SampleQueryRepository {

  private final SampleMapper sampleMapper;
  private final PageableSampleFactory pageableSampleFactory;

  @Override
  public PageableSample search(SampleQuery query) {
    final SampleQueryDto sampleQueryDto = sampleMapper.searchBy(query);
    if (Objects.isNull(sampleQueryDto))
      return pageableSampleFactory.create(0, Collections.emptyList());

    return pageableSampleFactory.create(sampleQueryDto.getTotal(), sampleQueryDto.getSamples());
  }
}
