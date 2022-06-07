package com.example.errorhandling.demo.application.service.exception;

public class UserNotFoundInDbException extends RuntimeException {

  public UserNotFoundInDbException(String msg) {
    super(msg);
  }
}
