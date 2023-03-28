package com.accelhack.application.api.app.controller;

import com.accelhack.accelparts.Request;
import com.accelhack.accelparts.ResponseSet;
import com.accelhack.accelparts.response.ListResponse;
import com.accelhack.application.api.app.entity.Sample;
import com.accelhack.application.api.app.entity.SampleSelector;
import com.accelhack.application.api.app.transaction.SampleTransaction;
import com.accelhack.application.api.shared.controller.base.ExternalController;
import com.accelhack.application.api.shared.functional.ParameterizedApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class SampleExtControllerImpl extends ExternalController implements SampleController {

  private final SampleTransaction sampleTransaction;

  @Override
  public ResponseEntity<ResponseSet<Sample>> get(Request<Sample> sampleRequest) {
    ParameterizedApi<Sample, Sample> callable = sampleTransaction::get;
    return execute(sampleRequest, callable);
  }

  @Override
  public ResponseEntity<ResponseSet<ListResponse<Sample>>> search(Request<SampleSelector> sampleRequest) {
    ParameterizedApi<SampleSelector, ListResponse<Sample>> callable = sampleTransaction::search;
    return execute(sampleRequest, callable);
  }

  @Override
  public ResponseEntity<ResponseSet<Sample>> add(Request<Sample> sampleRequest) {
    ParameterizedApi<Sample, Sample> callable = sampleTransaction::add;
    return execute(sampleRequest, callable);
  }

  @Override
  public ResponseEntity<ResponseSet<Sample>> edit(Request<Sample> sampleRequest) {
    ParameterizedApi<Sample, Sample> callable = sampleTransaction::edit;
    return execute(sampleRequest, callable);
  }

  @Override
  public ResponseEntity<ResponseSet<Sample>> remove(Request<Sample> sampleRequest) {
    ParameterizedApi<Sample, Sample> callable = sampleTransaction::remove;
    return execute(sampleRequest, callable);
  }
}