package com.accelhack.application.api.app.service;

import com.accelhack.application.api.app.dto.SampleDto;
import com.accelhack.application.api.app.entity.SampleSelector;
import com.accelhack.application.api.app.mapper.SampleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SampleServiceImpl implements SampleService {

  private final SampleMapper sampleMapper;

  @Override
  public SampleDto get(int id) {
    return sampleMapper.select(id);
  }

  @Override
  public List<SampleDto> search(SampleSelector sampleSelector) {
    return sampleMapper.selectBy(sampleSelector);
  }

  @Override
  public SampleDto add(SampleDto sampleDto) {
    SampleDto sd = sampleDto.toCreate();
    if (sampleMapper.insert(sd) != 1) {
      return null;
    }
    return sd;
  }

  @Override
  public SampleDto edit(SampleDto sampleDto) {
    SampleDto sd = sampleDto.toUpdate();
    if (sampleMapper.update(sd) != 1) {
      return null;
    }
    return sd;
  }

  @Override
  public SampleDto remove(SampleDto sampleDto) {
    SampleDto sd = sampleDto.toDelete();
    SampleDto res = sampleMapper.select(sd.getId());
    if (sampleMapper.delete(sd) != 1) {
      return null;
    }
    return res;
  }
}
