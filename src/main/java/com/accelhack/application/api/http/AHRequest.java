package com.accelhack.application.api.http;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.*;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class AHRequest<Req extends AHOperand> {
  private Long timestamp = new Date().getTime();

  @NonNull
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Req operand;

  public <Res> AHResponseSet<Res> validate(Validator validator, Class<?>... groups) {
    List<AHResponseError> requestErrors = toList(validator.validate(this, groups));
    if (!requestErrors.isEmpty()) {
      return AHResponseSet.error(HttpStatus.OK, requestErrors);
    }
    List<AHResponseError> operandErrors = toList(validator.validate(operand));
    if (!operandErrors.isEmpty()) {
      return AHResponseSet.operandError(HttpStatus.OK, requestErrors);
    }
    return null;
  }

  private <T> List<AHResponseError> toList(Set<ConstraintViolation<T>> violations) {
    return violations.stream().map(AHResponseError::build).toList();
  }
}
