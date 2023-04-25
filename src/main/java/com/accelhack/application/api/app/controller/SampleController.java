package com.accelhack.application.api.app.controller;

import com.accelhack.accelparts.Request;
import com.accelhack.accelparts.ResponseSet;
import com.accelhack.accelparts.response.ListResponse;
import com.accelhack.application.api.app.entity.Sample;
import com.accelhack.application.api.app.entity.SampleSelector;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface SampleController {
  String CONTEXT_PATH = "sample";

  @PostMapping(CONTEXT_PATH + "/get")
  ResponseEntity<ResponseSet<Sample>> get(@RequestBody Request<Sample> sampleRequest);

  @PostMapping(CONTEXT_PATH + "/search")
  ResponseEntity<ResponseSet<ListResponse<Sample>>> search(
      @RequestBody Request<SampleSelector> sampleRequest);

  @PostMapping(CONTEXT_PATH + "/add")
  ResponseEntity<ResponseSet<Sample>> add(@RequestBody Request<Sample> sampleRequest);

  @PostMapping(CONTEXT_PATH + "/edit")
  ResponseEntity<ResponseSet<Sample>> edit(@RequestBody Request<Sample> sampleRequest);

  @PostMapping(CONTEXT_PATH + "/remove")
  ResponseEntity<ResponseSet<Sample>> remove(@RequestBody Request<Sample> sampleRequest);
}
