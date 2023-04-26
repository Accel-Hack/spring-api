package com.accelhack.application.api.http;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.collect.Streams;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Deprecated
@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class Request<Req extends Operand> {
  private Long timestamp = new Date().getTime();
  @NonNull
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private List<Req> operands;

  public Req findFirst() {
    return operands.stream().findFirst().orElseThrow();
  }

  public <Res> ResponseSet<Res> validate(Validator validator, Class<?>... groups) {
    List<ResponseError> requestErrors = validator.validate(this, groups).stream()
        .map(error -> ResponseError.build("key", operands, "CODE")).collect(Collectors.toList());
    if (!requestErrors.isEmpty()) {
      return (new ResponseSet.Builder<Res>()).status(HttpStatus.OK).errors(requestErrors).build();
    }

    List<Response<Res>> responses = Streams.mapWithIndex(operands.stream(), (operand, index) -> {
      Set<ConstraintViolation<Operand>> operandErrors = validator.validate(operand);
      if (operandErrors.isEmpty()) {
        return null;
      }

      List<ResponseError> responseErrors = operandErrors.stream().map(error -> ResponseError
          .build(error.getPropertyPath().toString(), error.getInvalidValue(), error.getMessage()))
          .collect(Collectors.toList());
      return (new Response.Builder<Res>()).operationKey(Math.toIntExact(index))
          .operationStatus(false).errors(responseErrors).build();
    }).filter(Objects::nonNull).collect(Collectors.toList());

    if (!responses.isEmpty()) {
      return ResponseSet.ok(responses.size(), responses);
    }
    return null;
  }

  public Stream<Req> toStream() {
    return getOperands().stream();
  }
}
