package com.accelhack.application.api.shared.controller;

import com.accelhack.application.api.shared.controller.base.BaseController;
import com.accelhack.application.api.shared.registry.AuthRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class SignInControllerImpl extends BaseController implements SignInController {
  private final AuthRegistry authRegistry;

  @Override
  public String signIn() {
    return "signIn";
  }

  @Override
  public String signingIn() {
    return switch (authRegistry.getLoginUser().getActor()) {
      case SYSTEM -> "redirect:" + UIController.SYSTEM_CONTEXT + "/";
      case MANAGER -> "redirect:" + UIController.MANAGER_CONTEXT + "/";
      case USER -> "redirect:" + UIController.USER_CONTEXT + "/";
    };
  }
}
