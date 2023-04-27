package com.accelhack.application.api.base.domain;

import com.accelhack.application.api.shared.model.Operator;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static java.lang.Math.round;

@Getter
@Setter
public class Apilog {
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

  public void finished(HttpServletResponse response, Exception exception) {
    this.executionDurationMs =
        round((Instant.now().getNano() - operationTime.getNano()) / 1000.0) / 1000.0;
    this.status = response.getStatus();
    // FIXME: set response body
    Optional.ofNullable(exception).ifPresent(e -> this.exception = e.getLocalizedMessage());
  }
}
