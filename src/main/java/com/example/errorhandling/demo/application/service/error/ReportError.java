package com.example.errorhandling.demo.application.service.error;

public sealed interface ReportError extends GeneralError {
  record ReportForUserNotFoundError(String email) implements ReportError { }
  record InvalidReportNameError(String wrongName) implements ReportError { }
  record ReportClientUnexpectedError(Throwable cause) implements ReportError { }
}
