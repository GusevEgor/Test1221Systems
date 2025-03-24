package org.example.test1221systems.dto.foodintake;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FoodItemRequest {
    @NotNull
    @Positive
    private Long foodId;
    @NotNull
    @Positive
    private Long number;
}
