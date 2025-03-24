package org.example.test1221systems.dto.report;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.test1221systems.dto.foodintake.FoodIntakeResponse;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DailyReportResponse {
    Long userId;
    LocalDate date;
    Long caloric;
    Long proteins;
    Long fats;
    Long carbohydrates;
    List<FoodIntakeResponse> allFoodIntakes;
}
