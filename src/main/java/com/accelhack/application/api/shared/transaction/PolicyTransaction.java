package com.accelhack.application.api.shared.transaction;

import com.accelhack.accelparts.Request;
import com.accelhack.accelparts.ResponseSet;
import com.accelhack.application.api.shared.model.Policy;
import org.springframework.transaction.annotation.Transactional;

public interface PolicyTransaction {
  @Transactional(rollbackFor = Throwable.class)
  ResponseSet<Policy> create(Request<Policy> policyRequest);

  @Transactional(rollbackFor = Throwable.class)
  ResponseSet<Policy> remove(Request<Policy> policyRequest);
}