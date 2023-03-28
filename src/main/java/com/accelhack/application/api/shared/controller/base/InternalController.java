package com.accelhack.application.api.shared.controller.base;

import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(InternalController.CONTEXT_PATH)
public abstract class InternalController extends BaseController {
  public static final String CONTEXT_PATH = "/api/int/v1";
}
