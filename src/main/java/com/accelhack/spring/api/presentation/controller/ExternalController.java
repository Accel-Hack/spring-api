package com.accelhack.spring.api.presentation.controller;

import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(ExternalController.CONTEXT_PATH)
public abstract class ExternalController implements BaseController {
  public static final String CONTEXT_PATH = "/api/v1";
}
