package com.accelhack.application.api.app.service;

import com.accelhack.application.api.app.dto.SampleDto;
import com.accelhack.application.api.app.entity.SampleSelector;

import java.util.List;

public interface SampleService {
  SampleDto get(int id);

  List<SampleDto> search(SampleSelector sampleSelector);

  SampleDto add(SampleDto sampleDto);

  SampleDto edit(SampleDto sampleDto);

  SampleDto remove(SampleDto sampleDto);
}
