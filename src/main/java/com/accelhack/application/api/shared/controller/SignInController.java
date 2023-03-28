package com.accelhack.application.api.shared.controller;

import org.springframework.web.bind.annotation.GetMapping;

public interface SignInController {
  String SIGN_IN_PATH = "/signIn";
  @GetMapping(SIGN_IN_PATH)
  String signIn();

  @GetMapping("/signingIn")
  String signingIn();
}
