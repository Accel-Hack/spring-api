package com.accelhack.spring.api.presentation.controller;

import com.accelhack.commons.model.Request;
import com.accelhack.commons.model.ResponseSet;
import com.accelhack.spring.api.presentation.model.SampleModel;

import org.springframework.web.bind.annotation.*;

import java.util.UUID;

public interface SampleController {
  @GetMapping("sample")
  @ResponseBody
  ResponseSet<SampleModel.Entity> get(@RequestParam UUID id);

  @GetMapping("samples")
  @ResponseBody
  ResponseSet<SampleModel.ListEntity> search(@RequestParam(required = false) String name,
      @RequestParam(required = false) Integer limit,
      @RequestParam(required = false) Integer offset);

  @PutMapping("sample")
  @ResponseBody
  ResponseSet<SampleModel.Entity> add(@RequestBody Request<SampleModel.Create> sampleRequest);

  @PostMapping("sample")
  @ResponseBody
  ResponseSet<SampleModel.Entity> edit(@RequestBody Request<SampleModel.Update> sampleRequest);

  @DeleteMapping("sample")
  @ResponseBody
  ResponseSet<SampleModel.Entity> remove(@RequestBody Request<SampleModel.Delete> sampleRequest);
}
