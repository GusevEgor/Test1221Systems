package org.example.test1221systems.mapper;

import org.example.test1221systems.dto.foodintake.FoodIntakeRequest;
import org.example.test1221systems.dto.foodintake.FoodIntakeResponse;
import org.example.test1221systems.entity.FoodIntake;
import org.example.test1221systems.entity.FoodItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {FoodItemMapper.class})
public interface FoodIntakeMapper {

    @Mapping(target = "user", ignore = true)
    FoodIntake convertFromFoodIntakeRequestToEntity(FoodIntakeRequest foodIntakeRequest);

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "caloric", qualifiedByName = "computeCaloric", source = "foodItems")
    @Mapping(target= "proteins", qualifiedByName = "computeProteins", source = "foodItems")
    @Mapping(target= "fats", qualifiedByName = "computeFats", source = "foodItems")
    @Mapping(target= "carbohydrates", qualifiedByName = "computeCarbohydrates", source = "foodItems")
    FoodIntakeResponse convertFromEntityToFoodIntakeResponse(FoodIntake foodIntake);

    List<FoodIntakeResponse> convertFromListEntityToListFoodIntakeResponse(List<FoodIntake> foodIntakes);

    @Named("computeCaloric")
    default Long computeCaloric(List<FoodItem> foodItems) {
        return foodItems.stream().mapToLong(x -> x.getNumber() * x.getFood().getCaloric()).sum();
    }

    @Named("computeProteins")
    default Long computeProteins(List<FoodItem> foodItems) {
        return foodItems.stream().mapToLong(x -> x.getNumber() * x.getFood().getProteins()).sum();
    }

    @Named("computeFats")
    default Long computeFats(List<FoodItem> foodItems) {
        return foodItems.stream().mapToLong(x -> x.getNumber() * x.getFood().getFats()).sum();
    }

    @Named("computeCarbohydrates")
    default Long computeCarbohydrates(List<FoodItem> foodItems) {
        return foodItems.stream().mapToLong(x -> x.getNumber() * x.getFood().getCarbohydrates()).sum();
    }
}
