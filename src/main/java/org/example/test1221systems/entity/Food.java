package org.example.test1221systems.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "foods")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "caloric")
    private Long caloric;
    @Column(name = "proteins")
    private Long proteins;
    @Column(name = "fats")
    private Long fats;
    @Column(name = "carbohydrates")
    private Long carbohydrates;

    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL)
    private List<FoodItem> foodItems;

}
