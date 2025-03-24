package org.example.test1221systems.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.example.test1221systems.entity.Food;
import org.example.test1221systems.exceptions.NotFoundByIdException;
import org.example.test1221systems.mapper.FoodMapper;
import org.example.test1221systems.repository.FoodRepository;
import org.example.test1221systems.service.impl.FoodServiceImpl;
import org.example.test1221systems.dto.food.FoodRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;


import java.util.ArrayList;
import java.util.Optional;
import java.util.List;


public class FoodServiceImplTest {

    @Mock
    private FoodRepository foodRepository;

    @Mock
    private FoodMapper foodMapper;

    @InjectMocks
    private FoodServiceImpl foodService;

    private FoodRequest foodRequest;
    private Food food;

    @BeforeEach
    public void setUp() {;
        MockitoAnnotations.openMocks(this);

        foodRequest = new FoodRequest("Pizza", 300L, 10L, 15L, 40L);
        food = new Food(1L, "Pizza", 300L, 10L, 15L, 40L, new ArrayList<>());
    }

    @Test
    public void testCreateFood() {

        when(foodMapper.convertFromFoodRequestToEntity(foodRequest)).thenReturn(food);

        when(foodRepository.save(food)).thenReturn(food);

        Food result = foodService.create(foodRequest);

        assertNotNull(result);
        assertEquals(food.getTitle(), result.getTitle());
        verify(foodMapper).convertFromFoodRequestToEntity(foodRequest);
        verify(foodRepository).save(food);
    }

    @Test
    public void testUpdateFood() {
        Long foodId = 1L;
        when(foodRepository.findById(foodId)).thenReturn(Optional.of(food));
        when(foodRepository.save(food)).thenReturn(food);

        FoodRequest updatedFoodRequest =
                new FoodRequest("Updated Pizza", 350L, 12L, 18L, 45L);

        Food result = foodService.update(foodId, updatedFoodRequest);

        assertNotNull(result);
        assertEquals("Updated Pizza", result.getTitle());
        verify(foodRepository).findById(foodId);
        verify(foodRepository).save(food);
    }

    @Test
    public void testGetFood() {
        Long foodId = 1L;
        when(foodRepository.findById(foodId)).thenReturn(Optional.of(food));

        Food result = foodService.get(foodId);

        assertNotNull(result);
        assertEquals(food.getId(), result.getId());
        verify(foodRepository).findById(foodId);
    }

    @Test
    public void testGetFoodNotFound() {
        Long foodId = 999L;
        when(foodRepository.findById(foodId)).thenReturn(Optional.empty());

        assertThrows(NotFoundByIdException.class, () ->  foodService.get(foodId));

    }

    @Test
    public void testDeleteFood() {
        Long foodId = 1L;
        when(foodRepository.existsById(foodId)).thenReturn(true);

        foodService.delete(foodId);

        verify(foodRepository).existsById(foodId);
        verify(foodRepository).deleteById(foodId);
    }

    @Test
    public void testDeleteFoodNotFound() {
        Long foodId = 999L;

        when(foodRepository.existsById(foodId)).thenReturn(false);

        assertThrows(NotFoundByIdException.class, () -> foodService.delete(foodId));

    }

    @Test
    public void testGetAllFoods() {

        when(foodRepository.findAll()).thenReturn(List.of(food));

        List<Food> result = foodService.getAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(food.getTitle(), result.getFirst().getTitle());
        verify(foodRepository).findAll();
    }
}