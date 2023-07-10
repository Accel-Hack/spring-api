package com.accelhack.spring.api.presentation.controller;

import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(InternalController.CONTEXT_PATH)
public abstract class InternalController implements BaseController {
  public static final String CONTEXT_PATH = "/api/int/v1";
}
