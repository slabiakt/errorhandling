package com.example.errorhandling.demo.application.service.error;

public sealed interface UserError extends GeneralError {
   record UserNotFoundError(String userId) implements UserError { }
}
