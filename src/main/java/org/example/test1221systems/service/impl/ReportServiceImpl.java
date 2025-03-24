package org.example.test1221systems.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.test1221systems.dto.report.DailyReportResponse;
import org.example.test1221systems.dto.report.DailyStatusResponse;
import org.example.test1221systems.dto.report.HistoryReportResponse;
import org.example.test1221systems.dto.report.ReportDailyStatus;
import org.example.test1221systems.entity.FoodIntake;
import org.example.test1221systems.mapper.FoodIntakeMapper;
import org.example.test1221systems.repository.FoodIntakeRepository;
import org.example.test1221systems.service.ReportService;
import org.example.test1221systems.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final FoodIntakeRepository foodIntakeRepository;
    private final FoodIntakeMapper foodIntakeMapper;
    private final UserService userService;

    public DailyReportResponse getDailyReport(Long userId) {
        userService.checkUserExistsById(userId);
        LocalDate today = LocalDate.now();
        List<FoodIntake> foodIntakes = foodIntakeRepository.findAllByDateByUserId(today, userId);

        log.info("Get daily report for user with id {}", userId);
        return createDailyResponse(userId, today, foodIntakes);
    }

    public DailyStatusResponse getDailyStatus(Long userId) {
        var user = userService.getUserById(userId);
        Long userTargetCaloric = user.getDailyCalorieIntake();
        LocalDate today = LocalDate.now();
        List<FoodIntake> foodIntakes = foodIntakeRepository.findAllByDateByUserId(today, userId);

        Long dailyCaloric = foodIntakes
                .stream()
                .mapToLong(x -> x.getFoodItems()
                        .stream()
                        .mapToLong(f -> f.getNumber() * f.getFood().getCaloric())
                        .sum())
                .sum();

        ReportDailyStatus status = ReportDailyStatus.fromValues(userTargetCaloric, dailyCaloric);

        log.info("Get daily status for user with id {}", userId);
        return new DailyStatusResponse(userId, today, status);
    }

    public HistoryReportResponse getHistoryReport(Long userId) {
        userService.checkUserExistsById(userId);

        List<FoodIntake> allFoodIntakes = foodIntakeRepository.findAllByUserId(userId);
        List<LocalDate> userDates = foodIntakeRepository.findDistinctDateByUserId(userId);

        log.info("Get history report for user with id {}", userId);
        return new HistoryReportResponse(userId,
                userDates
                        .stream()
                        .map(day -> createDailyResponse(userId, day,
                                allFoodIntakes
                                        .stream()
                                        .filter(x -> x.getDate().isEqual(day)).toList()

                        ))
                        .toList());

    }

    private DailyReportResponse createDailyResponse(Long userId, LocalDate day, List<FoodIntake> foodIntakes) {

        DailyReportResponse response = new DailyReportResponse();
        response.setUserId(userId);
        response.setDate(day);

        response.setCaloric(foodIntakes
                .stream()
                .mapToLong(x -> x.getFoodItems()
                        .stream()
                        .mapToLong(f -> f.getNumber() * f.getFood().getCaloric())
                        .sum())
                .sum());

        response.setProteins(foodIntakes
                .stream()
                .mapToLong(x -> x.getFoodItems()
                        .stream()
                        .mapToLong(f -> f.getNumber() * f.getFood().getProteins())
                        .sum())
                .sum());

        response.setFats(foodIntakes
                .stream()
                .mapToLong(x -> x.getFoodItems()
                        .stream()
                        .mapToLong(f -> f.getNumber() * f.getFood().getFats())
                        .sum())
                .sum());

        response.setCarbohydrates(foodIntakes
                .stream()
                .mapToLong(x -> x.getFoodItems()
                        .stream()
                        .mapToLong(f -> f.getNumber() * f.getFood().getCarbohydrates())
                        .sum())
                .sum());

        var foodIntakeResponse = foodIntakeMapper.convertFromListEntityToListFoodIntakeResponse(foodIntakes);
        response.setAllFoodIntakes(foodIntakeResponse);

        return response;
    }
}
