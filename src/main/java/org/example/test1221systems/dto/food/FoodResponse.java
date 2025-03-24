package org.example.test1221systems.dto.food;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FoodResponse {
    private Long id;

    private String title;

    private Long caloric;

    private Long proteins;

    private Long fats;

    private Long carbohydrates;
}
