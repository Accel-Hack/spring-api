package com.accelhack.application.api.shared.utils;

import com.accelhack.application.api.http.*;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class ValidatorUtils {
  private final Validator validator;

  public <Req, Res> AHResponseSet<Res> validate(Req var1, Class<?>... var2) {
    return switch (var1) {
      case AHRequest<?> request -> request.validate(validator);
      default -> toResponseSet(validator.validate(var1, var2));
    };
  }

  private <Req, Res> AHResponseSet<Res> toResponseSet(Set<ConstraintViolation<Req>> violations) {
    List<AHResponseError> errors = violations.stream().map(AHResponseError::build).toList();
    if (!errors.isEmpty()) {
      return AHResponseSet.operandError(HttpStatus.OK, errors);
    }
    return null;
  }
}
