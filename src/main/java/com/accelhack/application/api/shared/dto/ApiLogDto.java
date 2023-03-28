package com.accelhack.application.api.shared.dto;

import com.accelhack.application.api.shared.dto.base.BaseDto;
import com.accelhack.application.api.shared.model.Operator;
import com.accelhack.application.api.shared.exception.NotImplementedException;
import lombok.*;

import java.time.Instant;

@AllArgsConstructor
@Data
@Builder
public class ApiLogDto implements BaseDto<ApiLogDto> {
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

  @Override
  public ApiLogDto toCreate() {
    return ApiLogDto.builder()
      .operationTime(operationTime)
      .operator(operator)
      .method(method)
      .path(path)
      .query(query)
      .requestBody(requestBody)
      .remoteIp(remoteIp)
      .executionTimeMs(executionTimeMs)
      .status(status)
      .exception(exception)
      .build();
  }

  @Override
  public ApiLogDto toUpdate() {
    throw new NotImplementedException();
  }

  @Override
  public ApiLogDto toDelete() {
    throw new NotImplementedException();
  }
}
