package com.accelhack.application.api.shared.exception;

public class NoSuchUserException extends RuntimeException {
  public NoSuchUserException(String username) {
    super(String.format("No such user(username=%s) exists.", username));
  }
}
