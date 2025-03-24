package org.example.test1221systems.service;

import org.example.test1221systems.dto.foodintake.FoodIntakeRequest;
import org.example.test1221systems.dto.foodintake.FoodItemRequest;
import org.example.test1221systems.entity.Food;
import org.example.test1221systems.entity.FoodIntake;
import org.example.test1221systems.entity.FoodItem;
import org.example.test1221systems.entity.User;
import org.example.test1221systems.exceptions.NotFoundByIdException;
import org.example.test1221systems.exceptions.NotFoundResourceByIdException;
import org.example.test1221systems.mapper.FoodIntakeMapper;
import org.example.test1221systems.mapper.FoodItemMapper;
import org.example.test1221systems.repository.FoodIntakeRepository;
import org.example.test1221systems.repository.FoodItemRepository;
import org.example.test1221systems.service.impl.FoodIntakeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.List;
import java.util.Arrays;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class FoodIntakeServiceImplTest {

    @Mock
    private FoodIntakeRepository foodIntakeRepository;

    @Mock
    private FoodIntakeMapper foodIntakeMapper;

    @Mock
    private FoodItemMapper foodItemMapper;

    @Mock
    private FoodItemRepository foodItemRepository;

    @Mock
    private UserService userService;

    @Mock
    private FoodService foodService;

    @InjectMocks
    private FoodIntakeServiceImpl foodIntakeService;

    private FoodIntakeRequest foodIntakeRequest;
    private FoodIntake foodIntake;
    private FoodItem foodItem;
    private User user;
    private Food food;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Пример данных
        foodIntakeRequest = new FoodIntakeRequest(
                List.of(new FoodItemRequest(1L, 2L))
        );

        user = new User();
        user.setId(1L);
        food = new Food(1L, "Pizza", 300L, 10L, 15L, 40L, new ArrayList<>());
        foodIntake = new FoodIntake(1L, LocalDate.now(), user, List.of(new FoodItem(1L, 2L, food, foodIntake)));
        foodItem = new FoodItem(1L, 2L, food, foodIntake);


    }

    @Test
    public void testCreateFoodIntake() {
        when(foodIntakeMapper.convertFromFoodIntakeRequestToEntity(foodIntakeRequest)).thenReturn(foodIntake);
        when(userService.getUserById(1L)).thenReturn(user);
        when(foodItemMapper.convertFromFoodItemRequestToEntity(any())).thenReturn(foodItem);
        when(foodService.get(1L)).thenReturn(food);
        when(foodIntakeRepository.save(foodIntake)).thenReturn(foodIntake);
        when(foodItemRepository.saveAll(anyList())).thenReturn(List.of(foodItem));

        FoodIntake result = foodIntakeService.create(1L, foodIntakeRequest);

        assertNotNull(result);
        assertEquals(1, result.getFoodItems().size());
        verify(foodIntakeRepository).save(foodIntake);
        verify(foodItemRepository).saveAll(anyList());
    }

    @Test
    public void testGetFoodIntakeById() {
        when(foodIntakeRepository.findById(1L)).thenReturn(Optional.of(foodIntake));

        FoodIntake result = foodIntakeService.get(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(foodIntakeRepository).findById(1L);
    }

    @Test
    public void testGetFoodIntakeByIdNotFound() {
        when(foodIntakeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundByIdException.class, () -> foodIntakeService.get(1L));
    }

    @Test
    public void testDeleteFoodIntake() {
        when(foodIntakeRepository.findById(1L)).thenReturn(Optional.of(foodIntake));

        foodIntakeService.delete(1L, 1L);

        verify(foodIntakeRepository).deleteById(1L);
    }

    @Test
    public void testDeleteFoodIntakeNotFound() {
        when(foodIntakeRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NotFoundByIdException.class, () -> foodIntakeService.delete(1L, 1L));

    }

    @Test
    public void testDeleteFoodIntakeForbidden() {
        User anotherUser = new User();
        anotherUser.setId(2L);

        foodIntake.setUser(anotherUser);
        when(foodIntakeRepository.findById(1L)).thenReturn(Optional.of(foodIntake));


        assertThrows(NotFoundResourceByIdException.class, () -> foodIntakeService.delete(1L, 1L));
    }
}