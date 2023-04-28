package com.accelhack.application.api.sample.controller.impl;

import com.accelhack.application.api.base.controller.base.ExternalController;
import com.accelhack.application.api.http.AHRequest;
import com.accelhack.application.api.http.AHResponseSet;
import com.accelhack.application.api.sample.controller.SampleController;
import com.accelhack.application.api.sample.model.SampleModel;
import com.accelhack.application.api.sample.usecase.SampleUsecase;
import com.accelhack.application.api.shared.utils.ValidatorUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.Objects;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class SampleExtControllerImpl extends ExternalController implements SampleController {

  private final ValidatorUtils validatorUtils;
  private final SampleUsecase sampleUsecase;


  /**
   * curl "http://localhost:8080/api/v1/sample?id=ee4d8f69-7b37-45b2-ba55-08a23e429ec3"
   */
  @Override
  public AHResponseSet<SampleModel.Entity> get(UUID id) {
    // use case
    SampleModel.Entity entity = sampleUsecase.get(id);

    // response
    return AHResponseSet.ok(entity);
  }

  /**
   * curl 'http://localhost:8080/api/v1/samples?' curl
   * "http://localhost:8080/api/v1/samples?name=k&limit=1&offset=10"
   */
  @Override
  public AHResponseSet<SampleModel.ListEntity> search(String name, Integer limit, Integer offset) {
    // create object
    SampleModel.Selector selector = new SampleModel.Selector(name, limit, offset);

    // validation
    AHResponseSet<SampleModel.ListEntity> error = validatorUtils.validate(selector);
    if (Objects.nonNull(error))
      return error;

    // use case
    SampleModel.ListEntity entity = sampleUsecase.search(selector);

    // response
    return AHResponseSet.ok(entity);
  }

  /**
   * curl -X PUT -H 'Content-Type: application/json' -d '{"operand":
   * {"name":"kengo","birthday":"1993-02-23T00:00:00Z","isJapanese": true}}'
   * http://localhost:8080/api/v1/sample
   */
  @Override
  public AHResponseSet<SampleModel.Entity> add(AHRequest<SampleModel.Create> sampleRequest) {
    // validation
    AHResponseSet<SampleModel.Entity> error = validatorUtils.validate(sampleRequest);
    if (Objects.nonNull(error))
      return error;

    // use case
    SampleModel.Entity entity = sampleUsecase.add(sampleRequest.getOperand());

    // response
    return AHResponseSet.ok(entity);
  }

  /**
   * curl -X POST -H 'Content-Type: application/json' -d
   * '{"operand":{"id":"ee4d8f69-7b37-45b2-ba55-08a23e429ec3", "name": "kengo",
   * "birthday":"1993-02-23T00:00:00Z","isJapanese": true}}' http://localhost:8080/api/v1/sample
   */
  @Override
  public AHResponseSet<SampleModel.Entity> edit(AHRequest<SampleModel.Update> sampleRequest) {
    // validation
    AHResponseSet<SampleModel.Entity> error = validatorUtils.validate(sampleRequest);
    if (Objects.nonNull(error))
      return error;

    // use case
    SampleModel.Entity entity = sampleUsecase.edit(sampleRequest.getOperand());

    // response
    return AHResponseSet.ok(entity);
  }

  /**
   * curl -X DELETE -H 'Content-Type: application/json' -d '{"operand":
   * {"id":"f32b76d3-6972-4b62-b19c-1d31bfc88e54"}}' http://localhost:8080/api/v1/sample
   */
  @Override
  public AHResponseSet<SampleModel.Entity> remove(AHRequest<SampleModel.Delete> sampleRequest) {
    // validation
    AHResponseSet<SampleModel.Entity> error = validatorUtils.validate(sampleRequest);
    if (Objects.nonNull(error))
      return error;

    // use case
    SampleModel.Entity entity = sampleUsecase.remove(sampleRequest.getOperand());

    // response
    return AHResponseSet.ok(entity);
  }
}
