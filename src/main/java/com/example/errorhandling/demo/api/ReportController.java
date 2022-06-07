package com.example.errorhandling.demo.api;

import com.example.errorhandling.demo.application.service.ReportService;
import com.example.errorhandling.demo.application.service.UserService;
import com.example.errorhandling.demo.domain.Report;
import com.example.errorhandling.demo.domain.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping(value = "/v1", produces = {MediaType.APPLICATION_JSON_VALUE})
@RestController
@AllArgsConstructor
public class ReportController {

  private final ReportService reportService;
  private final UserService userService;

  @GetMapping("/report")
  public Report getReport(@RequestParam String userId,@RequestParam String reportName) {
    User user = userService.getUserV1(userId);
    return reportService.getReportV1(user.email(), reportName);
  }

}


