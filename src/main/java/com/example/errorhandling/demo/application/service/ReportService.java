package com.example.errorhandling.demo.application.service;

import com.example.errorhandling.demo.application.service.error.GeneralError;
import com.example.errorhandling.demo.application.service.error.ReportError.InvalidReportNameError;
import com.example.errorhandling.demo.application.service.error.ReportError.ReportClientUnexpectedError;
import com.example.errorhandling.demo.application.service.error.ReportError.ReportForUserNotFoundError;
import com.example.errorhandling.demo.application.service.exception.ReportClientUnexpectedException;
import com.example.errorhandling.demo.application.service.exception.ReportNameNotFoundInReportApiException;
import com.example.errorhandling.demo.application.service.exception.UserNotFoundInReportApiException;
import com.example.errorhandling.demo.domain.Report;
import com.example.errorhandling.demo.external.ReportClient;
import com.example.errorhandling.demo.external.exception.InvalidReportNameException;
import com.example.errorhandling.demo.external.exception.ReportForUserNotFoundException;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReportService {

  private final ReportClient reportClient;

  public Either<GeneralError, Report> getReportV2(String email, String reportName) {
    return Try.of(() -> reportClient.getReport(email, reportName)).toEither()
      .mapLeft(reportServiceException -> switch (reportServiceException) {
        case InvalidReportNameException e -> new InvalidReportNameError(reportName);
        case ReportForUserNotFoundException e -> new ReportForUserNotFoundError(email);
        default -> new ReportClientUnexpectedError(reportServiceException);
      });
  }

  public Report getReportV1(String email, String reportName) {
    try {
      return reportClient.getReport(email, reportName);
    } catch (InvalidReportNameException e) {
      throw new ReportNameNotFoundInReportApiException();
    } catch (ReportForUserNotFoundException e) {
      throw new UserNotFoundInReportApiException();
    } catch (Exception e) {
      throw new ReportClientUnexpectedException(e);
    }
  }

}
