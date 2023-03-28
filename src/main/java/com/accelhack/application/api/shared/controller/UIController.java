package com.accelhack.application.api.shared.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public interface UIController {
  String USER_CONTEXT = "/app";
  String MANAGER_CONTEXT = "/manage";
  String SYSTEM_CONTEXT = "/system";

  @GetMapping(USER_CONTEXT + "/**")
  String app(Model model);

  @GetMapping(MANAGER_CONTEXT + "/**")
  String manager(Model model);

  @GetMapping(SYSTEM_CONTEXT + "/**")
  String system(Model model);
}
