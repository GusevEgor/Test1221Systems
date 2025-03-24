package org.example.test1221systems.dto.foodintake;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FoodIntakeResponse {
    private Long id;
    private Long userId;
    private LocalDate date;
    private Long caloric;
    private Long proteins;
    private Long fats;
    private Long carbohydrates;
    private List<FoodItemResponse> foodItems;

}
