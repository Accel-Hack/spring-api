package com.accelhack.application.api.shared.transaction;

import com.accelhack.application.api.http.Request;
import com.accelhack.application.api.http.ResponseSet;
import com.accelhack.application.api.shared.dto.SuperuserDto;
import com.accelhack.application.api.shared.model.Superuser;
import org.springframework.transaction.annotation.Transactional;

public interface SuperuserTransaction {
  SuperuserDto getSession(int id);

  @Transactional(rollbackFor = Throwable.class)
  void updateExpireTime(SuperuserDto superuserDto);

  @Transactional(rollbackFor = Throwable.class)
  ResponseSet<Superuser> startSession(Request<Superuser> userRequest);

  @Transactional(rollbackFor = Throwable.class)
  ResponseSet<Superuser> endSession(Request<Superuser> userRequest);
}
