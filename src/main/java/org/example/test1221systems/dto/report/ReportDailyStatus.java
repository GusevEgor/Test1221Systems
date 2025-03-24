package org.example.test1221systems.dto.report;

public enum ReportDailyStatus {
    OVER_TARGET,
    WITHIN_TARGET,
    UNDER_TARGET;

    public static ReportDailyStatus fromValues(Long targetValue, Long currentValue) {
        Long diff = targetValue - currentValue;

        if (diff > 500)
            return UNDER_TARGET;
        else if (diff < -500)
            return OVER_TARGET;
        else
            return WITHIN_TARGET;
    }
}
