package com.accelhack.application.api.sample.usecase;

import com.accelhack.application.api.sample.model.SampleModel;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


public interface SampleUsecase {
  @Transactional(readOnly = true)
  SampleModel.Entity get(UUID id);

  @Transactional(readOnly = true)
  SampleModel.ListEntity search(SampleModel.Selector selector);

  @Transactional(rollbackFor = Throwable.class)
  SampleModel.Entity add(SampleModel.Create sampleRequest);

  @Transactional(rollbackFor = Throwable.class)
  SampleModel.Entity edit(SampleModel.Update sampleRequest);

  @Transactional(rollbackFor = Throwable.class)
  SampleModel.Entity remove(SampleModel.Delete sampleRequest);
}
