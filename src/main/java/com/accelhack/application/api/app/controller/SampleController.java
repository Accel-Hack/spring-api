package com.accelhack.application.api.app.controller;

import com.accelhack.application.api.app.model.SampleModel;
import com.accelhack.application.api.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

public interface SampleController {
  @GetMapping("sample")
  @ResponseBody
  AHResponseSet<SampleModel.Entity> get(@RequestParam UUID id);

  @GetMapping("samples")
  @ResponseBody
  AHResponseSet<SampleModel.ListEntity> search(@RequestParam(required = false) String name,
      @RequestParam(required = false) Integer limit,
      @RequestParam(required = false) Integer offset);

  @PutMapping("sample")
  @ResponseBody
  AHResponseSet<SampleModel.Entity> add(@RequestBody AHRequest<SampleModel.Create> sampleRequest);

  @PostMapping("sample")
  @ResponseBody
  AHResponseSet<SampleModel.Entity> edit(@RequestBody AHRequest<SampleModel.Update> sampleRequest);

  @DeleteMapping("sample")
  @ResponseBody
  AHResponseSet<SampleModel.Entity> remove(
      @RequestBody AHRequest<SampleModel.Delete> sampleRequest);
}
