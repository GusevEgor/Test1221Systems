package org.example.test1221systems.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.test1221systems.dto.user.UserRequest;
import org.example.test1221systems.dto.user.UserResponse;
import org.example.test1221systems.mapper.UserMapper;
import org.example.test1221systems.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/create")
    public UserResponse create(@Valid @RequestBody UserRequest userRequest) {
        log.info("Post request with userRequest: {}", userRequest);
        return userMapper.convertFromUserToUserResponse(userService.create(userRequest));
    }


    @PutMapping("/update/{id}")
    public UserResponse updateUser(@PathVariable("id") Long id, @Valid @RequestBody UserRequest userRequest) {
        log.info("Put request user with id: {}, userRequest: {}", id, userRequest);
        return userMapper.convertFromUserToUserResponse(userService.update(id, userRequest));
    }

    @GetMapping("/all/targets")
    public List<String> getAllTargets() {
        log.info("Get request all targets");
        return userService.getAllUserTargets();
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable("id") Long id) {
        log.info("Get request user with id: {}", id);
        return userMapper.convertFromUserToUserResponse(userService.getUserById(id));
    }

    @GetMapping("/all")
    public List<UserResponse> getAllUsers() {
        log.info("Get request all users");
        return userMapper.convertFromListUserToListUserResponse(userService.getAllUsers());
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUserById(@PathVariable("id") Long id) {
        log.info("Delete request user with id: {}", id);
        userService.deleteUserById(id);
    }
}