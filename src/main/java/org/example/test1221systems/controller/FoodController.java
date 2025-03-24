package org.example.test1221systems.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.test1221systems.dto.food.FoodRequest;
import org.example.test1221systems.dto.food.FoodResponse;
import org.example.test1221systems.mapper.FoodMapper;
import org.example.test1221systems.service.FoodService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/foods")
@RequiredArgsConstructor
public class FoodController {
    private final FoodMapper foodMapper;
    private final FoodService foodService;

    @PostMapping("/create")
    public FoodResponse create(@Valid @RequestBody FoodRequest foodRequest) {
        log.info("Post request with foodRequest: {}", foodRequest);
        return foodMapper.convertFromEntityToFoodResponse(foodService.create(foodRequest));
    }

    @PutMapping("/update/{id}")
    public FoodResponse update(@PathVariable("id") Long id, @Valid @RequestBody FoodRequest foodRequest) {
        log.info("Put request food with id: {}, foodRequest: {}", id, foodRequest);
        return foodMapper.convertFromEntityToFoodResponse(foodService.update(id, foodRequest));
    }

    @GetMapping("get/{id}")
    public FoodResponse get(@PathVariable("id") Long id) {
        log.info("Get request food with id: {}", id);
        return foodMapper.convertFromEntityToFoodResponse(foodService.get(id));
    }

    @GetMapping("get/all")
    public List<FoodResponse> getAll() {
        log.info("Get request all food");
        return foodMapper.convertFromEntityListToFoodResponseList(foodService.getAll());
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        log.info("Delete request food with id: {}", id);
        foodService.delete(id);
    }

}
