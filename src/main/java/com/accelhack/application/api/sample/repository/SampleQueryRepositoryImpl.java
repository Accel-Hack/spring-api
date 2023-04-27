package com.accelhack.application.api.sample.repository;

import com.accelhack.application.api.sample.domain.PageableSample;
import com.accelhack.application.api.sample.domain.sample.SampleQueryRepository;
import com.accelhack.application.api.sample.dto.SampleQuery;
import com.accelhack.application.api.sample.dto.SampleQueryDto;
import com.accelhack.application.api.sample.mapper.SampleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SampleQueryRepositoryImpl implements SampleQueryRepository {

  private final SampleMapper sampleMapper;

  @Override
  public PageableSample search(SampleQuery query) {
    SampleQueryDto sampleQueryDto = sampleMapper.searchBy(query);
    return new PageableSample(sampleQueryDto.getTotal(), sampleQueryDto.getSamples(), query.limit(),
        query.offset());
  }
}
