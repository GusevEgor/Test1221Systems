package org.example.test1221systems.service;

import org.example.test1221systems.dto.food.FoodRequest;
import org.example.test1221systems.entity.Food;

import java.util.List;

public interface FoodService {
    Food create(FoodRequest foodRequest);

    Food update(Long id, FoodRequest foodRequest);

    Food get(Long id);

    List<Food> getAll();

    void delete(Long id);
}
