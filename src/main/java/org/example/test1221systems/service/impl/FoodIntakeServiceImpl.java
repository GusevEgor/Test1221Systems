package org.example.test1221systems.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.test1221systems.dto.foodintake.FoodIntakeRequest;
import org.example.test1221systems.entity.FoodIntake;
import org.example.test1221systems.entity.FoodItem;
import org.example.test1221systems.entity.User;
import org.example.test1221systems.exceptions.NotFoundByIdException;
import org.example.test1221systems.exceptions.NotFoundResourceByIdException;
import org.example.test1221systems.mapper.FoodIntakeMapper;
import org.example.test1221systems.mapper.FoodItemMapper;
import org.example.test1221systems.repository.FoodIntakeRepository;
import org.example.test1221systems.repository.FoodItemRepository;
import org.example.test1221systems.service.FoodIntakeService;
import org.example.test1221systems.service.FoodService;
import org.example.test1221systems.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FoodIntakeServiceImpl implements FoodIntakeService {

    private final FoodIntakeRepository foodIntakeRepository;
    private final FoodIntakeMapper foodIntakeMapper;
    private final FoodItemMapper foodItemMapper;
    private final FoodItemRepository foodItemRepository;
    private final UserService userService;
    private final FoodService foodService;

    public FoodIntake create(Long userId, FoodIntakeRequest foodIntakeRequest) {
        var foodIntake = foodIntakeMapper.convertFromFoodIntakeRequestToEntity(foodIntakeRequest);
        var user = userService.getUserById(userId);

        List<FoodItem> foodItems = foodIntakeRequest
                .getFoodItems()
                .stream()
                .map(x ->{
                        var item = foodItemMapper.convertFromFoodItemRequestToEntity(x);
                        item.setFood(foodService.get(x.getFoodId()));
                        item.setFoodIntake(foodIntake);
                        return item;})
                .toList();


        foodIntake.setFoodItems(foodItems);
        foodIntake.setUser(user);
        foodIntake.setDate(LocalDate.now());
        var savedFoodIntake = foodIntakeRepository.save(foodIntake);
        foodItemRepository.saveAll(foodItems);

        log.info("FoodItem has been created in the amount of {}", savedFoodIntake.getFoodItems().size());
        log.info("FoodIntake with id {} created", savedFoodIntake.getId());
        return savedFoodIntake;
    }


    public FoodIntake get(Long id) {
        return foodIntakeRepository.findById(id).orElseThrow(() -> new NotFoundByIdException(FoodIntake.class, id));
    }

    public List<FoodIntake> getAll() {
        return foodIntakeRepository.findAll();
    }

    public void delete(Long userId, Long id) {
        checkUserHasFoodIntakeAndGetIt(userId, id);
        foodIntakeRepository.deleteById(id);
    }

    private FoodIntake checkUserHasFoodIntakeAndGetIt(Long sellerId, Long foodIntakeId) {
        userService.checkUserExistsById(sellerId);
        var foodIntake = foodIntakeRepository.findById(foodIntakeId)
                .orElseThrow(() -> new NotFoundByIdException(FoodIntake.class, foodIntakeId));
        if (!foodIntake.getUser().getId().equals(sellerId)) {
            log.warn("User with id {} does not have foodIntake with id {}", sellerId, foodIntakeId);
            throw new NotFoundResourceByIdException(User.class, sellerId, FoodIntake.class, foodIntakeId);
        }
        return foodIntake;
    }
}
