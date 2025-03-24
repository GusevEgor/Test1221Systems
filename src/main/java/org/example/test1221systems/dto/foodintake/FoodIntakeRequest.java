package org.example.test1221systems.dto.foodintake;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FoodIntakeRequest {
    @Valid
    @NotNull
    private List<FoodItemRequest> foodItems;
}
