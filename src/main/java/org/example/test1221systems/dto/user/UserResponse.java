package org.example.test1221systems.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private Long age;
    private Long weight;
    private Long growth;
    private String target;
    private Long dailyCalorieIntake;
}
