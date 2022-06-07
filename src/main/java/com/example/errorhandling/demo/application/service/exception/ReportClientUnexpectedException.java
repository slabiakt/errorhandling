package com.example.errorhandling.demo.application.service.exception;

public class ReportClientUnexpectedException extends RuntimeException {

  public ReportClientUnexpectedException(Throwable cause) {
    super(cause);
  }
}
