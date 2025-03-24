package org.example.test1221systems.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.test1221systems.entity.targets.UserTarget;


import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "age")
    private Long age;

    @Column(name = "weight")
    private Long weight;

    @Column(name = "growth")
    private Long growth;

    @Column(name = "daily_calorie_intake")
    private Long dailyCalorieIntake;

    @Enumerated(EnumType.STRING)
    @Column(name = "target")
    private UserTarget target;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<FoodIntake> foodIntakes;

}
