package com.accelhack.application.api.sample.usecase.impl;

import com.accelhack.application.api.sample.domain.PageableSample;
import com.accelhack.application.api.sample.domain.Sample;
import com.accelhack.application.api.sample.domain.sample.*;
import com.accelhack.application.api.sample.dto.SampleQuery;
import com.accelhack.application.api.sample.model.SampleModel;
import com.accelhack.application.api.sample.usecase.SampleUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SampleUsecaseImpl implements SampleUsecase {
  private final SampleRepository sampleRepository;
  private final SampleQueryRepository sampleQueryRepository;

  private final SampleFactory sampleFactory;

  @Override
  public SampleModel.Entity get(UUID id) {
    Sample sample = sampleRepository.findByPk(id);
    return SampleModel.Entity.from(sample);
  }

  @Override
  public SampleModel.ListEntity search(SampleModel.Selector selector) {
    SampleQuery query =
        sampleFactory.buildQuery(selector.getName(), selector.getLimit(), selector.getOffset());
    PageableSample pageableSample = sampleQueryRepository.search(query);
    return SampleModel.ListEntity.from(selector, pageableSample.getTotal(),
        pageableSample.getSamples());
  }

  @Override
  public SampleModel.Entity add(SampleModel.Create sampleRequest) {
    Sample sample = sampleFactory.createFrom(sampleRequest.getName(), sampleRequest.getBirthday(),
        sampleRequest.getIsJapanese());
    sampleRepository.save(sample);
    return SampleModel.Entity.from(sample);
  }

  @Override
  public SampleModel.Entity edit(SampleModel.Update sampleRequest) {
    Sample sample = sampleRepository.findByPk(sampleRequest.getId());
    if (Objects.isNull(sample)) {
      // FIXME: not found
      return null;
    }

    sample.changeName(sampleRequest.getName());
    sample.changeBirthday(sampleRequest.getBirthday());
    sample.changeIsJapanese(sampleRequest.getIsJapanese());

    sampleRepository.save(sample);
    return SampleModel.Entity.from(sample);
  }

  @Override
  public SampleModel.Entity remove(SampleModel.Delete sampleRequest) {
    Sample sample = sampleRepository.findByPk(sampleRequest.getId());
    if (Objects.isNull(sample)) {
      // FIXME: already deleted
      return null;
    }
    sampleRepository.delete(sample.getId());
    return SampleModel.Entity.from(sample);
  }
}
