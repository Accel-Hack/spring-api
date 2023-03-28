package com.accelhack.application.api.shared.service;

import com.accelhack.application.api.shared.dto.SuperuserDto;
import com.accelhack.application.api.shared.mapper.SuperuserMapper;
import com.accelhack.application.api.shared.registry.AuthRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SuperuserServiceImpl implements SuperuserService {

  private final AuthRegistry authRegistry;

  private final SuperuserMapper superuserMapper;

  @Value("${api-application.super-user.expireMinutes:15}")
  int expireMinutes;

  @Override
  public SuperuserDto getById(int id) {
    return superuserMapper.selectById(id);
  }

  @Override
  public SuperuserDto create(SuperuserDto superuserDto) {
    // 1. delete all the other sudo records for oneself.
    delete(superuserDto);
    // 2. add sudo record
    SuperuserDto su = superuserDto.toCreate();
    superuserMapper.insert(su, expireMinutes, authRegistry.requireAuthorizedOperator());
    return su;
  }

  @Override
  public SuperuserDto updateExpireTime(SuperuserDto superuserDto) {
    SuperuserDto su = superuserDto.toUpdate();
    superuserMapper.update(su, expireMinutes, authRegistry.requireAuthorizedOperator());
    return su;
  }

  @Override
  public SuperuserDto delete(SuperuserDto superuserDto) {
    SuperuserDto su = superuserDto.toDelete();
    superuserMapper.delete(su, authRegistry.requireAuthorizedOperator());
    return su;
  }
}
