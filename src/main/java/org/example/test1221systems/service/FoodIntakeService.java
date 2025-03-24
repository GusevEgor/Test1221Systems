package org.example.test1221systems.service;

import org.example.test1221systems.dto.foodintake.FoodIntakeRequest;
import org.example.test1221systems.entity.FoodIntake;

import java.util.List;

public interface FoodIntakeService {

    FoodIntake create(Long userId, FoodIntakeRequest foodIntakeRequest);

    FoodIntake get(Long id);

    List<FoodIntake> getAll();

    void delete(Long userId, Long id);
}
