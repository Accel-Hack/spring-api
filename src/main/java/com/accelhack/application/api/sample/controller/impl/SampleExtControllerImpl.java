package com.accelhack.application.api.sample.controller.impl;

import com.accelhack.application.api.base.controller.base.ExternalController;
import com.accelhack.application.api.sample.controller.SampleController;
import com.accelhack.application.api.sample.model.SampleModel;
import com.accelhack.application.api.sample.usecase.SampleUsecase;
import com.accelhack.commons.model.Request;
import com.accelhack.commons.model.ResponseSet;
import com.accelhack.commons.model.utils.ValidatorUtils;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.Objects;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class SampleExtControllerImpl extends ExternalController implements SampleController {

  private final Validator validator;
  private final SampleUsecase sampleUsecase;


  /**
   * curl "http://localhost:8080/api/v1/sample?id=ee4d8f69-7b37-45b2-ba55-08a23e429ec3"
   */
  @Override
  public ResponseSet<SampleModel.Entity> get(UUID id) {
    // use case
    SampleModel.Entity entity = sampleUsecase.get(id);

    // response
    return ResponseSet.ok(entity);
  }

  /**
   * curl 'http://localhost:8080/api/v1/samples?' curl
   * "http://localhost:8080/api/v1/samples?name=k&limit=1&offset=10"
   */
  @Override
  public ResponseSet<SampleModel.ListEntity> search(String name, Integer limit, Integer offset) {
    // create object
    SampleModel.Selector selector = new SampleModel.Selector(name, limit, offset);

    // validation
    ResponseSet<SampleModel.ListEntity> error = ValidatorUtils.validate(validator, selector);
    if (Objects.nonNull(error))
      return error;

    // use case
    SampleModel.ListEntity entity = sampleUsecase.search(selector);

    // response
    return ResponseSet.ok(entity);
  }

  /**
   * curl -X PUT -H 'Content-Type: application/json' -d '{"operand":
   * {"name":"kengo","birthday":"1993-02-23T00:00:00Z","isJapanese": true}}'
   * http://localhost:8080/api/v1/sample
   */
  @Override
  public ResponseSet<SampleModel.Entity> add(Request<SampleModel.Create> sampleRequest) {
    // validation
    ResponseSet<SampleModel.Entity> error = ValidatorUtils.validate(validator, sampleRequest);
    if (Objects.nonNull(error))
      return error;

    // use case
    SampleModel.Entity entity = sampleUsecase.add(sampleRequest.getOperand());

    // response
    return ResponseSet.ok(entity);
  }

  /**
   * curl -X POST -H 'Content-Type: application/json' -d
   * '{"operand":{"id":"ee4d8f69-7b37-45b2-ba55-08a23e429ec3", "name": "kengo",
   * "birthday":"1993-02-23T00:00:00Z","isJapanese": true}}' http://localhost:8080/api/v1/sample
   */
  @Override
  public ResponseSet<SampleModel.Entity> edit(Request<SampleModel.Update> sampleRequest) {
    // validation
    ResponseSet<SampleModel.Entity> error = ValidatorUtils.validate(validator, sampleRequest);
    if (Objects.nonNull(error))
      return error;

    // use case
    SampleModel.Entity entity = sampleUsecase.edit(sampleRequest.getOperand());

    // response
    return ResponseSet.ok(entity);
  }

  /**
   * curl -X DELETE -H 'Content-Type: application/json' -d '{"operand":
   * {"id":"f32b76d3-6972-4b62-b19c-1d31bfc88e54"}}' http://localhost:8080/api/v1/sample
   */
  @Override
  public ResponseSet<SampleModel.Entity> remove(Request<SampleModel.Delete> sampleRequest) {
    // validation
    ResponseSet<SampleModel.Entity> error = ValidatorUtils.validate(validator, sampleRequest);
    if (Objects.nonNull(error))
      return error;

    // use case
    SampleModel.Entity entity = sampleUsecase.remove(sampleRequest.getOperand());

    // response
    return ResponseSet.ok(entity);
  }
}
