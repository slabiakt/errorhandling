package com.example.errorhandling.demo.application.service.error;

public sealed interface GeneralError permits ReportError, UserError, ServiceError {

}
