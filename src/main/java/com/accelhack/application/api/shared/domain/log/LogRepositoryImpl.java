package com.accelhack.application.api.shared.domain.log;

import com.accelhack.application.api.shared.mapper.LogMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LogRepositoryImpl implements LogRepository {
  private final LogMapper mapper;

  @Override
  public Log add(Log log) {
    mapper.insert(log);
    return log;
  }
}
