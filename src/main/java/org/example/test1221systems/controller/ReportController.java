package org.example.test1221systems.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.test1221systems.dto.report.DailyReportResponse;
import org.example.test1221systems.dto.report.DailyStatusResponse;
import org.example.test1221systems.dto.report.HistoryReportResponse;
import org.example.test1221systems.service.ReportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/reports")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    @GetMapping("/daily/{userId}")
    public DailyReportResponse getDailyReport(@PathVariable Long userId) {
        log.info("Get daily report for user with id {}", userId);
        return reportService.getDailyReport(userId);
    }

    @GetMapping("/daily/status/{userId}")
    public DailyStatusResponse getDailyStatusReport(@PathVariable Long userId) {
        log.info("Get daily status for user with id {}", userId);
        return reportService.getDailyStatus(userId);
    }

    @GetMapping("history/{userId}")
    public HistoryReportResponse getHistoryReport(@PathVariable Long userId) {
        log.info("Get history report for user with id {}", userId);
        return reportService.getHistoryReport(userId);
    }
}
