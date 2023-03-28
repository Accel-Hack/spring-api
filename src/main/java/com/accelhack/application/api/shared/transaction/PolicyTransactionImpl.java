package com.accelhack.application.api.shared.transaction;

import com.accelhack.accelparts.Request;
import com.accelhack.accelparts.ResponseSet;
import com.accelhack.accelparts.executor.OperandExecutor;
import com.accelhack.application.api.shared.model.Policy;
import com.accelhack.application.api.shared.service.PolicyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.Validator;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class PolicyTransactionImpl implements PolicyTransaction {

  private final Validator validator;
  private final PolicyService policyService;

  @Override
  public ResponseSet<Policy> create(Request<Policy> policyRequest) {
    Function<Policy, Policy> func = policy ->
      Policy.from(policyService.create(policy.toPolicyDto()));
    return OperandExecutor.build(func).run(policyRequest, validator, Policy.CreateData.class);
  }

  @Override
  public ResponseSet<Policy> remove(Request<Policy> policyRequest) {
    Function<Policy, Policy> func = policy ->
      Policy.from(policyService.remove(policy.toPolicyDto()));
    return OperandExecutor.build(func).run(policyRequest, validator, Policy.RemoveData.class);
  }
}