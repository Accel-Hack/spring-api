package com.accelhack.spring.api.sample.repository;

import com.accelhack.spring.api.sample.domain.PageableSample;
import com.accelhack.spring.api.sample.domain.SampleQuery;
import com.accelhack.spring.api.sample.domain.pageable.PageableSampleFactory;
import com.accelhack.spring.api.sample.mapper.SampleMapper;
import com.accelhack.spring.api.sample.domain.sample.SampleQueryRepository;
import com.accelhack.spring.api.sample.dto.SampleQueryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Objects;

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
