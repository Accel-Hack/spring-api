package com.accelhack.spring.api.shared.controller.impl;

import com.accelhack.spring.api.shared.controller.abstracts.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(InternalController.CONTEXT_PATH)
public abstract class InternalController implements BaseController {
  public static final String CONTEXT_PATH = "/api/int/v1";
}
