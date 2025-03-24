package org.example.test1221systems.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.example.test1221systems.dto.report.DailyReportResponse;
import org.example.test1221systems.dto.report.DailyStatusResponse;
import org.example.test1221systems.dto.report.HistoryReportResponse;
import org.example.test1221systems.dto.report.ReportDailyStatus;
import org.example.test1221systems.entity.Food;
import org.example.test1221systems.entity.FoodIntake;
import org.example.test1221systems.entity.FoodItem;
import org.example.test1221systems.entity.User;
import org.example.test1221systems.mapper.FoodIntakeMapper;
import org.example.test1221systems.repository.FoodIntakeRepository;
import org.example.test1221systems.repository.UserRepository;
import org.example.test1221systems.service.impl.ReportServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;


import java.time.LocalDate;
import java.util.List;


public class ReportServiceImplTest {

    @Mock
    private FoodIntakeRepository foodIntakeRepository;

    @Mock
    private FoodIntakeMapper foodIntakeMapper;

    @Mock
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ReportServiceImpl reportService;

    private User user;
    private FoodIntake foodIntake;
    private FoodItem foodItem;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new User();
        user.setId(1L);
        user.setUsername("John");
        user.setDailyCalorieIntake(2500L);

        foodIntake = new FoodIntake();
        foodIntake.setDate(LocalDate.now());
        foodIntake.setUser(user);

        foodItem = new FoodItem();
        foodItem.setNumber(2L);
        foodItem.setFood(new Food());
        foodItem.getFood().setCaloric(100L);
        foodItem.getFood().setProteins(5L);
        foodItem.getFood().setFats(2L);
        foodItem.getFood().setCarbohydrates(15L);

        foodIntake.setFoodItems(List.of(foodItem));
    }

    @Test
    public void testGetDailyReport() {
        when(userRepository.existsById(1L)).thenReturn(true);

        when(foodIntakeRepository.findAllByDateByUserId(LocalDate.now(), 1L)).thenReturn(List.of(foodIntake));

        DailyReportResponse response = reportService.getDailyReport(1L);


        assertNotNull(response);
        assertEquals(1L, response.getUserId());
        assertEquals(LocalDate.now(), response.getDate());
        assertEquals(200L, response.getCaloric());
        assertEquals(10L, response.getProteins());
        assertEquals(4L, response.getFats());
        assertEquals(30L, response.getCarbohydrates());
    }

    @Test
    public void testGetDailyStatus() {

        when(userService.getUserById(1L)).thenReturn(user);
        when(foodIntakeRepository.findAllByDateByUserId(LocalDate.now(), 1L)).thenReturn(List.of(foodIntake));

        DailyStatusResponse response = reportService.getDailyStatus(1L);


        assertNotNull(response);
        assertEquals(1L, response.getUserId());
        assertEquals(LocalDate.now(), response.getDate());
        assertEquals(ReportDailyStatus.UNDER_TARGET, response.getMessage());
    }

    @Test
    public void testGetHistoryReport() {
        // Мокаем поведение зависимостей
        when(foodIntakeRepository.findAllByUserId(1L)).thenReturn(List.of(foodIntake));
        when(foodIntakeRepository.findDistinctDateByUserId(1L)).thenReturn(List.of(LocalDate.now()));

        HistoryReportResponse response = reportService.getHistoryReport(1L);


        assertNotNull(response);
        assertEquals(1L, response.getUserId());
        assertEquals(1, response.getHistory().size());
        assertEquals(LocalDate.now(), response.getHistory().getFirst().getDate());
    }


}
