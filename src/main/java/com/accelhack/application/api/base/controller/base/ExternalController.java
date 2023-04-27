package com.accelhack.application.api.base.controller.base;

import com.accelhack.application.api.shared.utils.ValidatorUtils;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(ExternalController.CONTEXT_PATH)
public abstract class ExternalController extends BaseController {
  public static final String CONTEXT_PATH = "/api/v1";

  public ExternalController(ValidatorUtils validatorUtils) {
    super(validatorUtils);
  }
}
