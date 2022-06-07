package com.example.errorhandling.demo.application.service.exception;

public class DatabaseAccessException extends RuntimeException {

  public DatabaseAccessException(Throwable cause) {
    super(cause);
  }
}
