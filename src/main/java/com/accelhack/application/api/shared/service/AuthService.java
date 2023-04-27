package com.accelhack.application.api.shared.service;

import com.accelhack.application.api.base.domain.User;
import com.accelhack.application.api.base.domain.user.UserRepository;
import com.accelhack.application.api.shared.model.Operator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AuthService {

  private final UserRepository userRepository;

  public Operator getOperator() {
    User user = getLoginUser();
    if (Objects.isNull(user))
      return Operator.ANONYMOUS;

    return new Operator(user);
  }

  public Operator requireAuthorizedOperator() {
    return new Operator(Objects.requireNonNull(getLoginUser()));
  }

  public UUID getUserId() {
    return Objects.requireNonNull(getLoginUser()).getId();
  }

  private User getLoginUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (Objects.isNull(authentication))
      return null;

    return userRepository.findByUsername(authentication.getName());
  }
}
