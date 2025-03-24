package org.example.test1221systems.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.example.test1221systems.entity.targets.UserTarget;
import org.example.test1221systems.mapper.UserMapper;
import org.example.test1221systems.repository.UserRepository;
import org.example.test1221systems.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.example.test1221systems.dto.user.UserRequest;
import org.example.test1221systems.entity.User;
import org.example.test1221systems.exceptions.NotFoundByIdException;

import java.util.Optional;

public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;



    @InjectMocks
    private UserServiceImpl userService;

    private UserRequest userRequest;
    private User user;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        userRequest = new UserRequest();
        userRequest.setUsername("John");
        userRequest.setAge(25L);
        userRequest.setGrowth(180L);
        userRequest.setWeight(75L);
        userRequest.setEmail("john.doe@example.com");
        userRequest.setTarget("WEIGHT_LOSS");

        user = new User();
        user.setId(1L);
        user.setUsername("John");
        user.setAge(25L);
        user.setGrowth(180L);
        user.setWeight(75L);
        user.setEmail("john.doe@example.com");
        user.setTarget(UserTarget.WEIGHT_LOSS);
    }

    @Test
    public void testCreateValidUser() {

        when(userMapper.convertFromUserRequestToEntity(userRequest)).thenReturn(user);
        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = userService.create(userRequest);

        assertNotNull(result);
        assertEquals("John", result.getUsername());
        assertEquals(25, result.getAge());
        assertEquals(180, result.getGrowth());
        assertEquals(75, result.getWeight());
    }

    @Test
    public void testUpdateUser() {

        when(userRepository.existsById(1L)).thenReturn(true);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        userRequest.setUsername("UpdatedUser");

        User result = userService.update(1L, userRequest);

        assertNotNull(result);
        assertEquals("UpdatedUser", result.getUsername());
    }

    @Test
    public void testGetUserById() {

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User result = userService.getUserById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    public void testDeleteUserById() {

        when(userRepository.existsById(1L)).thenReturn(true);

        userService.deleteUserById(1L);

        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testCheckUserExists() {
        when(userRepository.existsById(1L)).thenReturn(true);

        userService.checkUserExistsById(1L);

        assertDoesNotThrow(() -> userService.checkUserExistsById(1L));
    }

    @Test
    public void testCheckUserExists_UserNotFound() {
        when(userRepository.existsById(1L)).thenReturn(false);

        assertThrows(NotFoundByIdException.class, () -> userService.checkUserExistsById(1L));
    }
}