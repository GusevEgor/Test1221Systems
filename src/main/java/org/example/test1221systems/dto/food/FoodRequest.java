package org.example.test1221systems.dto.food;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FoodRequest {
    @NotNull
    @NotBlank
    private String title;
    @NotNull
    @PositiveOrZero
    private Long caloric;
    @NotNull
    @PositiveOrZero
    private Long proteins;
    @NotNull
    @PositiveOrZero
    private Long fats;
    @NotNull
    @PositiveOrZero
    private Long carbohydrates;
}

