package com.accelhack.application.api.base.controller.base;

import com.accelhack.application.api.shared.utils.ValidatorUtils;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(InternalController.CONTEXT_PATH)
public abstract class InternalController extends BaseController {
  public static final String CONTEXT_PATH = "/api/int/v1";

  public InternalController(ValidatorUtils validatorUtils) {
    super(validatorUtils);
  }
}
