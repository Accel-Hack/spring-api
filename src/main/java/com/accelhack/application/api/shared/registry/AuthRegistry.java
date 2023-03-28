package com.accelhack.application.api.shared.registry;

import com.accelhack.application.api.shared.dto.UserDto;
import com.accelhack.application.api.shared.model.Operator;
import com.accelhack.application.api.shared.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class AuthRegistry {

  private final UserService userService;

  public Operator getOperator() {
    return new Operator(getLoginUser());
  }

  public Operator requireAuthorizedOperator() {
    UserDto user = getLoginUser();
    assert Objects.nonNull(user);
    return new Operator(user);
  }

  public int getUserId() {
    return Objects.requireNonNull(getLoginUser()).getId();
  }

  public UserDto getLoginUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (Objects.isNull(authentication))
      return null;

    return userService.getByUsername(authentication.getName());
  }
}
