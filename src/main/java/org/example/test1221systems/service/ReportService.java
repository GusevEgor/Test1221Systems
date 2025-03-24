package org.example.test1221systems.service;

import org.example.test1221systems.dto.report.DailyReportResponse;
import org.example.test1221systems.dto.report.DailyStatusResponse;
import org.example.test1221systems.dto.report.HistoryReportResponse;

public interface ReportService {

    DailyReportResponse getDailyReport(Long userId);

    DailyStatusResponse getDailyStatus(Long userId);

    HistoryReportResponse getHistoryReport(Long userId);
}
