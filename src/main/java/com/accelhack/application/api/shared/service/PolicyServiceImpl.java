package com.accelhack.application.api.shared.service;

import com.accelhack.application.api.shared.dto.PolicyDto;
import com.accelhack.application.api.shared.dto.RolePolicyDto;
import com.accelhack.application.api.shared.mapper.PolicyMapper;
import com.accelhack.application.api.shared.mapper.RolePolicyMapper;
import com.accelhack.application.api.shared.registry.AuthRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PolicyServiceImpl implements PolicyService {

  private final AuthRegistry authRegistry;
  private final PolicyMapper policyMapper;
  private final RolePolicyMapper rolePolicyMapper;

  @Override
  public PolicyDto create(PolicyDto policyDto) {
    PolicyDto policy = policyDto.toCreate();
    policyMapper.insert(policy, authRegistry.requireAuthorizedOperator());
    return policy;
  }

  @Override
  public PolicyDto remove(PolicyDto policyDto) {
    // Delete policy
    PolicyDto policy = policyDto.toCreate();
    policyMapper.delete(policy, authRegistry.requireAuthorizedOperator());

    // Delete bind of policies
    RolePolicyDto rolePolicyDto = RolePolicyDto.builder().policyId(policy.getId()).build().toDelete();
    rolePolicyMapper.delete(rolePolicyDto, authRegistry.requireAuthorizedOperator());

    return policy;
  }
}
