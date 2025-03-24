package org.example.test1221systems.dto.foodintake;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FoodItemResponse {
    private Long foodId;
    private String title;
    private Long number;
    private Long caloric;
    private Long proteins;
    private Long fats;
    private Long carbohydrates;
}
