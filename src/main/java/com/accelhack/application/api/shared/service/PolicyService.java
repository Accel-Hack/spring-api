package com.accelhack.application.api.shared.service;

import com.accelhack.application.api.shared.dto.PolicyDto;

public interface PolicyService {
  PolicyDto create(PolicyDto policyDto);

  PolicyDto remove(PolicyDto policyDto);
}
