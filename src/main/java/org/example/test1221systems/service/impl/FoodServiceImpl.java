package org.example.test1221systems.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.test1221systems.dto.food.FoodRequest;
import org.example.test1221systems.entity.Food;
import org.example.test1221systems.exceptions.NotFoundByIdException;
import org.example.test1221systems.mapper.FoodMapper;
import org.example.test1221systems.repository.FoodRepository;
import org.example.test1221systems.service.FoodService;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;
    private final FoodMapper foodMapper;

    public Food create(FoodRequest foodRequest) {
        var food = foodMapper.convertFromFoodRequestToEntity(foodRequest);
        log.info("Food with id {} created", food.getId());
        return foodRepository.save(food);
    }

    public Food update(Long id, FoodRequest foodRequest) {
        var food = foodRepository.findById(id).orElseThrow(() -> new NotFoundByIdException(Food.class, id));
        food.setTitle(foodRequest.getTitle());
        food.setCaloric(foodRequest.getCaloric());
        food.setProteins(foodRequest.getProteins());
        food.setFats(foodRequest.getFats());
        food.setCarbohydrates(foodRequest.getCarbohydrates());
        log.info("Food with id {} updated", food.getId());
        return foodRepository.save(food);
    }

    public Food get(Long id) {
        return foodRepository.findById(id).orElseThrow(() -> new NotFoundByIdException(Food.class, id));
    }

    public List<Food> getAll() {
        return foodRepository.findAll();
    }

    public void delete(Long id) {
        checkFoodExists(id);
        log.info("Food with id {} deleted", id);
        foodRepository.deleteById(id);
    }

    public void checkFoodExists(Long id) {
        if (!foodRepository.existsById(id)) {
            log.warn("Food with id {} not found", id);
            throw new NotFoundByIdException(Food.class, id);
        }
    }

}
