package com.accelhack.application.api.app.transaction;

import com.accelhack.accelparts.Request;
import com.accelhack.accelparts.ResponseSet;
import com.accelhack.accelparts.response.ListResponse;
import com.accelhack.application.api.app.entity.Sample;
import com.accelhack.application.api.app.entity.SampleSelector;
import org.springframework.transaction.annotation.Transactional;

public interface SampleTransaction {
  ResponseSet<Sample> get(Request<Sample> sampleRequest);

  ResponseSet<ListResponse<Sample>> search(Request<SampleSelector> sampleRequest);

  @Transactional(rollbackFor = Throwable.class)
  ResponseSet<Sample> add(Request<Sample> sampleRequest);

  @Transactional(rollbackFor = Throwable.class)
  ResponseSet<Sample> edit(Request<Sample> sampleRequest);

  @Transactional(rollbackFor = Throwable.class)
  ResponseSet<Sample> remove(Request<Sample> sampleRequest);
}
