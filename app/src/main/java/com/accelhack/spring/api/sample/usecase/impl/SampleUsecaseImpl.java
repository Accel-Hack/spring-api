package com.accelhack.spring.api.sample.usecase.impl;

import com.accelhack.spring.api.sample.domain.*;
import com.accelhack.spring.api.sample.domain.sample.*;
import com.accelhack.spring.api.sample.model.SampleModel;
import com.accelhack.spring.api.sample.usecase.SampleUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

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
    final Sample sample =
        Optional.ofNullable(sampleRepository.findByPk(sampleRequest.getId())).orElseThrow() // FIXME:
                                                                                            // 存在しない時の処理を書く
            .toBuilder().name(sampleRequest.getName()).birthday(sampleRequest.getBirthday())
            .isJapanese(sampleRequest.getIsJapanese()).build();

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
