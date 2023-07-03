package com.accelhack.spring.api.infrastructure.dto;

import java.time.Instant;
import java.util.UUID;

import com.accelhack.spring.api.domain.model.log.Apilog;
import com.accelhack.spring.api.domain.model.shared.Operator;

import lombok.Getter;

@Getter
public class ApilogDto {
  private UUID id;
  private Operator operator;
  private String sessionId;
  private String remoteAddress;
  private Instant operationTime;
  private double executionDurationMs;
  private String method;
  private String path;
  private String query;
  private String body;
  private String response;
  private Integer status;
  private String exception;

  public static ApilogDto from(Apilog apilog) {
    ApilogDto apilogDto = new ApilogDto();
    apilogDto.id = apilog.getId();
    apilogDto.operator = apilog.getOperator();
    apilogDto.sessionId = apilog.getSessionId();
    apilogDto.remoteAddress = apilog.getRemoteAddress();
    apilogDto.operationTime = apilog.getOperationTime();
    apilogDto.executionDurationMs = apilog.getExecutionDurationMs();
    apilogDto.method = apilog.getMethod();
    apilogDto.path = apilog.getPath();
    apilogDto.query = apilog.getQuery();
    apilogDto.body = apilog.getBody();
    apilogDto.response = apilog.getResponse();
    apilogDto.status = apilog.getStatus();
    apilogDto.exception = apilog.getException();
    return apilogDto;
  }

  public Apilog toApilogDomain() {
    return Apilog.builder().id(id).operator(operator).sessionId(sessionId)
        .remoteAddress(remoteAddress).operationTime(operationTime)
        .executionDurationMs(executionDurationMs).method(method).path(path).query(query).body(body)
        .response(response).status(status).exception(exception).build();
  }
}
