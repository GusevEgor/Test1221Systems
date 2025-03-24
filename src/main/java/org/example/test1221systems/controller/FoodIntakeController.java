package org.example.test1221systems.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.test1221systems.dto.foodintake.FoodIntakeRequest;
import org.example.test1221systems.dto.foodintake.FoodIntakeResponse;
import org.example.test1221systems.mapper.FoodIntakeMapper;
import org.example.test1221systems.service.FoodIntakeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/food-intakes")
@RequiredArgsConstructor
public class FoodIntakeController {
    private final FoodIntakeService foodIntakeService;
    private final FoodIntakeMapper foodIntakeMapper;

    @PostMapping("/create/{userId}")
    public FoodIntakeResponse create(@PathVariable Long userId, @Valid @RequestBody FoodIntakeRequest foodIntakeRequest) {
        log.info("Post request with foodIntakeRequest: {}", foodIntakeRequest);
        return foodIntakeMapper.convertFromEntityToFoodIntakeResponse(foodIntakeService.create(userId, foodIntakeRequest));
    }

    @GetMapping("/get{id}")
    public FoodIntakeResponse get(@PathVariable Long id) {
        log.info("Get request foodIntake with id: {}", id);
        return foodIntakeMapper.convertFromEntityToFoodIntakeResponse(foodIntakeService.get(id));
    }

    @GetMapping("get/all")
    public List<FoodIntakeResponse> getAll() {
        log.info("Get request all foodIntake");
        return foodIntakeMapper.convertFromListEntityToListFoodIntakeResponse(foodIntakeService.getAll());
    }

    @DeleteMapping("delete/{userId}/{id}")
    public void delete(@PathVariable Long userId, @PathVariable Long id) {
        log.info("Delete request foodIntake with id: {}", id);
        foodIntakeService.delete(userId, id);
    }
}
