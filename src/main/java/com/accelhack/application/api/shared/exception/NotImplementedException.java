package com.accelhack.application.api.shared.exception;

public class NotImplementedException extends RuntimeException {
  public NotImplementedException() {
    super("No such action exists.");
  }

  public NotImplementedException(RuntimeException e) {
    super("No such action exists.", e);
  }
}
