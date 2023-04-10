package com.accelhack.application.api.shared.domain.log;

import com.accelhack.application.api.shared.model.Operator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Instant;

@NoArgsConstructor(access = AccessLevel.PRIVATE) // for JPA
public class Log {
  private Integer id;
  private Instant operationTime;
  private Operator operator;
  private String method;
  private String path;
  private String query;
  private String requestBody;
  private String remoteIp;
  private double executionTimeMs;
  private int status;
  private String exception;
}
