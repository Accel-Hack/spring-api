package com.accelhack.application.api.shared.domain.log;

import com.accelhack.application.api.shared.model.Operator;

public interface LogRepository {
  Log add(Log log, Operator operator);
}
