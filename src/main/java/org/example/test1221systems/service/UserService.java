package org.example.test1221systems.service;

import org.example.test1221systems.dto.user.UserRequest;
import org.example.test1221systems.entity.User;

import java.util.List;

public interface UserService {
    User create(UserRequest userRequest);

    User update(Long id, UserRequest userRequest);

    List<User> getAllUsers();

    User getUserById(Long id);

    List<String> getAllUserTargets();

    void deleteUserById(Long id);

    void checkUserExistsById(Long id);
}
