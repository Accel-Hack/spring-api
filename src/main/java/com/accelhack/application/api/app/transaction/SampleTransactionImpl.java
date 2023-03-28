package com.accelhack.application.api.app.transaction;

import com.accelhack.accelparts.Request;
import com.accelhack.accelparts.ResponseSet;
import com.accelhack.accelparts.executor.OperandExecutor;
import com.accelhack.accelparts.response.ListResponse;
import com.accelhack.application.api.app.dto.SampleDto;
import com.accelhack.application.api.app.entity.Sample;
import com.accelhack.application.api.app.entity.SampleSelector;
import com.accelhack.application.api.app.service.SampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.Validator;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SampleTransactionImpl implements SampleTransaction {
  private final Validator validator;
  private final SampleService sampleService;

  protected Sample getSample(Sample sample) {
    return Sample.from(sampleService.get(sample.getId()));
  }

  @Override
  public ResponseSet<Sample> get(Request<Sample> sampleRequest) {
    return OperandExecutor.build(this::getSample).run(sampleRequest, validator);
  }

  protected ListResponse<Sample> searchSample(SampleSelector selector) {
    List<SampleDto> samples = sampleService.search(selector);
    return samples.isEmpty() ? ListResponse.build(0, 0, Collections.emptyList())
      : ListResponse.build(samples.get(0).getTotal(), 0, samples.stream().map(Sample::from).toList());
  }

  @Override
  public ResponseSet<ListResponse<Sample>> search(Request<SampleSelector> sampleRequest) {
    return OperandExecutor.build(this::searchSample).run(sampleRequest, validator);
  }

  protected Sample addSample(Sample sample) {
    return Sample.from(sampleService.add(sample.toSampleDto()));
  }

  @Override
  public ResponseSet<Sample> add(Request<Sample> sampleRequest) {
    return OperandExecutor.build(this::addSample).run(sampleRequest, validator);
  }

  protected Sample editSample(Sample sample) {
    return Sample.from(sampleService.edit(sample.toSampleDto()));
  }

  @Override
  public ResponseSet<Sample> edit(Request<Sample> sampleRequest) {
    return OperandExecutor.build(this::editSample).run(sampleRequest, validator);
  }

  protected Sample removeSample(Sample sample) {
    return Sample.from(sampleService.remove(sample.toSampleDto()));
  }

  @Override
  public ResponseSet<Sample> remove(Request<Sample> sampleRequest) {
    return OperandExecutor.build(this::removeSample).run(sampleRequest, validator);
  }
}
