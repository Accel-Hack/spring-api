package com.accelhack.application.api.shared.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Controller
public class ExceptionController {

  @ExceptionHandler({MethodArgumentTypeMismatchException.class})
  public String exception() {
    return "error";
  }
}
