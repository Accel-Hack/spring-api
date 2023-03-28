package com.accelhack.application.api.shared.controller;

import com.accelhack.application.api.shared.controller.base.BaseController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UIControllerImpl extends BaseController implements UIController {
  @Override
  public String app(Model model) {
    // get allowed pages
    return "index";
  }

  @Override
  public String manager(Model model) {
    // get allowed pages
    return "index";
  }

  @Override
  public String system(Model model) {
    // get allowed pages
    return "index";
  }
}
