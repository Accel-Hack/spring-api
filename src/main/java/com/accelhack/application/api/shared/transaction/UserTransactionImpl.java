package com.accelhack.application.api.shared.transaction;

import com.accelhack.application.api.http.*;
import com.accelhack.application.api.shared.dto.UserDto;
import com.accelhack.application.api.shared.model.User;
import com.accelhack.application.api.shared.model.UserSelector;
import com.accelhack.application.api.shared.service.UserService;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class UserTransactionImpl implements UserTransaction {
  private final Validator validator;
  private final UserService userService;

  @Override
  public ResponseSet<ListResponse<User>> search(Request<UserSelector> userRequest) {
    Function<UserSelector, ListResponse<User>> func = selector -> {
      List<UserDto> users = userService.search(selector);
      return users.isEmpty() ? ListResponse.build(0, 0, Collections.emptyList())
          : ListResponse.build(users.get(0).getTotal(), 0, users.stream().map(User::from).toList());
    };
    return OperandExecutor.build(func).run(userRequest, validator);
  }
}
