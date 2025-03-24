package org.example.test1221systems.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.test1221systems.dto.user.UserRequest;
import org.example.test1221systems.entity.User;
import org.example.test1221systems.entity.targets.UserTarget;
import org.example.test1221systems.exceptions.NotFoundByIdException;
import org.example.test1221systems.mapper.UserMapper;
import org.example.test1221systems.repository.UserRepository;
import org.example.test1221systems.service.UserService;
import org.springframework.stereotype.Service;


import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public void checkUserExistsById(Long id) {
        if (!userRepository.existsById(id)) {
            log.warn("User with id {} not found", id);
            throw new NotFoundByIdException(User.class, id);
        }
    }

    public User create(UserRequest userRequest) {
        var user = userMapper.convertFromUserRequestToEntity(userRequest);
        user.setDailyCalorieIntake(computeDailyCalories(userRequest, user.getTarget()));
        log.info("User with id {} created", user.getId());
        return userRepository.save(user);
    }

    public User update(Long id, UserRequest userRequest) {
        checkUserExistsById(id);
        var user = userRepository.findById(id).orElseThrow(() -> new NotFoundByIdException(User.class, id));
        user.setUsername(userRequest.getUsername());
        user.setAge(userRequest.getAge());
        user.setGrowth(userRequest.getGrowth());
        user.setWeight(userRequest.getWeight());
        user.setEmail(userRequest.getEmail());

        user.setDailyCalorieIntake(computeDailyCalories(userRequest, user.getTarget()));
        log.info("User with id {} updated", user.getId());
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundByIdException(User.class, id));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<String> getAllUserTargets() {
        return UserTarget.getAllTargets();
    }

    public void deleteUserById(Long id) {
        checkUserExistsById(id);
        log.info("User with id {} deleted", id);
        userRepository.deleteById(id);
    }


    private Long computeDailyCalories(UserRequest userRequest, UserTarget userTarget) {
        Long calories = Math.round(88.36 + (13.4 * userRequest.getWeight()) +
                (4.8 * userRequest.getGrowth()) - (5.7 * userRequest.getAge()));

        return switch (userTarget) {
            case WEIGHT_LOSS -> Math.round(calories * 1.2);
            case MAINTENANCE -> Math.round(calories * 1.55);
            case WEIGHT_GAIN -> Math.round(calories * 1.725);
        };
    }
}
