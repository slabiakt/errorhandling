package com.example.errorhandling.demo.application.service.error;

public sealed interface ServiceError extends GeneralError {
   record DatabaseError(Throwable cause) implements ServiceError { }
   record ReportApiError(Throwable cause) implements ServiceError { }
}
