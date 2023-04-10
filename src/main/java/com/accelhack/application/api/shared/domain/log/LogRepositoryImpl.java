package com.accelhack.application.api.shared.domain.log;

import com.accelhack.application.api.shared.model.Operator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LogRepositoryImpl implements LogRepository {
  @Override
  public Log add(Log log, Operator operator) {
    return null;
  }
}
