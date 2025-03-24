package org.example.test1221systems.dto.report;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DailyStatusResponse {
    Long userId;
    LocalDate date;
    ReportDailyStatus message;
}
