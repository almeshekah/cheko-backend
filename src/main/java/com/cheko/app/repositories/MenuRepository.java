package com.cheko.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cheko.app.entities.MenuEntity;
import com.cheko.app.entities.CategoryEntity;

@Repository
public interface MenuRepository extends JpaRepository<MenuEntity, Long>, JpaSpecificationExecutor<MenuEntity> {

    Long countByCategory(CategoryEntity category);

    List<MenuEntity> findByCategory(CategoryEntity category);

    List<MenuEntity> findByCategory_NameIgnoreCase(String category);

    List<MenuEntity> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String q1, String q2);

    List<MenuEntity> findByCategoryOrderByCaloriesDesc(CategoryEntity category);

    @Query(value = """
            WITH ranked_menu AS (
                SELECT m.*,
                       ROW_NUMBER() OVER (PARTITION BY m.category_id ORDER BY m.calories DESC) as calorie_rank
                FROM menu m
            )
            SELECT * FROM ranked_menu
            WHERE calorie_rank = 2
            ORDER BY category_id, calories DESC
            """, nativeQuery = true)
    List<MenuEntity> findSecondHighestCaloriePerCategory();

}
