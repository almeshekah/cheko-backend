package com.cheko.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.cheko.app.entities.MenuEntity;
import com.cheko.app.entities.CategoryEntity;

@Repository
public interface MenuRepository extends JpaRepository<MenuEntity, Long>, JpaSpecificationExecutor<MenuEntity> {

    Long countByCategory(CategoryEntity category);

    List<MenuEntity> findByCategory_NameIgnoreCase(String category);

    List<MenuEntity> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String q1, String q2);

}
