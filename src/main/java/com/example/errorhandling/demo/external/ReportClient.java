package com.example.errorhandling.demo.external;

import com.example.errorhandling.demo.domain.Report;
import com.example.errorhandling.demo.external.exception.InvalidReportNameException;
import com.example.errorhandling.demo.external.exception.ReportForUserNotFoundException;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.stereotype.Component;

@Component
public class ReportClient {

  public Report getReport(String email, String reportName) {
    return switch (ThreadLocalRandom.current().nextInt(0, 10)) {
      case 0 -> throw new InvalidReportNameException();
      case 1 -> throw new ReportForUserNotFoundException();
      default -> new Report("someReportValue for " + email + " and " + reportName);
    };
  }

}

