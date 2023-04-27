package com.accelhack.application.api.base.controller.base;

import com.accelhack.application.api.shared.utils.ValidatorUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public abstract class BaseController {
  protected final ValidatorUtils validatorUtils;
}
