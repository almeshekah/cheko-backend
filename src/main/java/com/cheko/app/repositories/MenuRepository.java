package com.cheko.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cheko.app.entities.MenuEntity;

@Repository
public interface MenuRepository extends JpaRepository<MenuEntity, Long> {

}
