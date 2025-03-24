package org.example.test1221systems.mapper;

import org.example.test1221systems.dto.food.FoodRequest;
import org.example.test1221systems.dto.food.FoodResponse;
import org.example.test1221systems.entity.Food;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface FoodMapper {
    Food convertFromFoodRequestToEntity(FoodRequest foodRequest);

    FoodResponse convertFromEntityToFoodResponse(Food food);

    List<FoodResponse> convertFromEntityListToFoodResponseList(List<Food> foodList);
}
