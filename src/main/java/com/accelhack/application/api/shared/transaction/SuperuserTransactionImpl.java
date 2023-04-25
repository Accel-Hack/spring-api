package com.accelhack.application.api.shared.transaction;

import com.accelhack.accelparts.Request;
import com.accelhack.accelparts.ResponseSet;
import com.accelhack.accelparts.executor.OperandExecutor;
import com.accelhack.application.api.shared.dto.SuperuserDto;
import com.accelhack.application.api.shared.model.Superuser;
import com.accelhack.application.api.shared.registry.AuthRegistry;
import com.accelhack.application.api.shared.service.SuperuserService;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class SuperuserTransactionImpl implements SuperuserTransaction {
  private final Validator validator;
  private final AuthRegistry authRegistry;
  private final SuperuserService superuserService;

  @Override
  public SuperuserDto getSession(int id) {
    return superuserService.getById(id);
  }

  @Override
  public void updateExpireTime(SuperuserDto superuserDto) {
    superuserService.updateExpireTime(superuserDto);
  }

  @Override
  public ResponseSet<Superuser> startSession(Request<Superuser> userRequest) {
    Function<Superuser, Superuser> func = su -> {
      su.setSuperuserId(authRegistry.getUserId());
      return Superuser.from(superuserService.create(su.toSuperuserDto()));
    };
    return OperandExecutor.build(func).run(userRequest, validator);
  }

  @Override
  public ResponseSet<Superuser> endSession(Request<Superuser> userRequest) {
    Function<Superuser, Superuser> func = su -> {
      su.setSuperuserId(authRegistry.getUserId());
      return Superuser.from(superuserService.delete(su.toSuperuserDto()));
    };
    return OperandExecutor.build(func).run(userRequest, validator);
  }
}
