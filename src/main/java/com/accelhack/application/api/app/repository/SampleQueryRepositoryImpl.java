package com.accelhack.application.api.app.repository;

import com.accelhack.application.api.app.domain.PageableSample;
import com.accelhack.application.api.app.domain.sample.SampleQueryRepository;
import com.accelhack.application.api.app.dto.SampleDto;
import com.accelhack.application.api.app.dto.SampleQuery;
import com.accelhack.application.api.app.mapper.SampleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SampleQueryRepositoryImpl implements SampleQueryRepository {

  private final SampleMapper sampleMapper;

  @Override
  public PageableSample search(SampleQuery query) {
    List<SampleDto> samples = sampleMapper.searchBy(query);
    return new PageableSample(samples.size(),
        samples.stream().map(SampleDto::toSampleDomain).toList(), query.limit(), query.offset());
  }
}
