package org.example.test1221systems.repository;

import org.example.test1221systems.entity.FoodIntake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface FoodIntakeRepository extends JpaRepository<FoodIntake, Long> {

    @Query("""
            SELECT f
            FROM FoodIntake f
            WHERE f.date = :firstDay
            AND f.user.id = :userId
            """)
    List<FoodIntake> findAllByDateByUserId(@Param("firstDay") LocalDate date, @Param("userId") Long userId);

    List<FoodIntake> findAllByUserId(Long userId);

    @Query("SELECT DISTINCT f.date FROM FoodIntake f WHERE f.user.id = :userId ORDER BY f.date DESC")
    List<LocalDate> findDistinctDateByUserId(Long userId);


}
