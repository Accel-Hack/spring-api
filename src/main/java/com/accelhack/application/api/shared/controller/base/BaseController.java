package com.accelhack.application.api.shared.controller.base;

import com.accelhack.application.api.http.*;
import com.accelhack.application.api.shared.exception.NotImplementedException;
import com.accelhack.application.api.shared.functional.ParameterizedApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

import java.util.concurrent.Callable;

@Slf4j
public abstract class BaseController {

  public <O extends Operand, R> ResponseEntity<ResponseSet<R>> execute(Request<O> request,
      ParameterizedApi<O, R> callable) {
    return execute(() -> callable.call(request));
  }

  public <R> ResponseEntity<ResponseSet<R>> execute(Callable<ResponseSet<R>> callable) {
    ResponseSet<R> result;
    try {
      result = callable.call();
    } catch (Throwable t) {
      log.error(t.getLocalizedMessage());
      result = ResponseSet.internalServerError();
    }
    return buildResponse(result);
  }

  private <E> ResponseEntity<ResponseSet<E>> buildResponse(ResponseSet<E> resultSet) {
    return switch (resultSet.getStatus()) {
      case OK -> ResponseEntity.ok(resultSet);
      case BAD_REQUEST, UNAUTHORIZED, FORBIDDEN -> ResponseEntity.badRequest().body(resultSet);
      case INTERNAL_SERVER_ERROR -> ResponseEntity.internalServerError().body(resultSet);
      default -> throw new NotImplementedException();
    };
  }
}
