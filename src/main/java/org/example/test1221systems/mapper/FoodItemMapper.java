package org.example.test1221systems.mapper;

import org.example.test1221systems.dto.foodintake.FoodItemRequest;
import org.example.test1221systems.dto.foodintake.FoodItemResponse;
import org.example.test1221systems.entity.FoodItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface FoodItemMapper {

    @Mapping(target = "food", ignore = true)
    @Mapping(target = "foodIntake", ignore = true)
    @Mapping(target = "id", ignore = true)
    FoodItem convertFromFoodItemRequestToEntity(FoodItemRequest foodItemRequest);

    @Mapping(target = "foodId", source = "foodItem.food.id")
    @Mapping(target = "title", source = "foodItem.food.title")
    @Mapping(target = "number", source = "foodItem.number")
    @Mapping(target = "caloric", source = "foodItem.food.caloric")
    @Mapping(target = "proteins", source = "foodItem.food.proteins")
    @Mapping(target = "fats", source = "foodItem.food.fats")
    @Mapping(target = "carbohydrates", source = "foodItem.food.carbohydrates")
    FoodItemResponse convertFromEntityToFoodItemResponse(FoodItem foodItem);

}
