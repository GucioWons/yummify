package com.guciowons.yummify.menu.infrastructure.repository;

import com.guciowons.yummify.menu.domain.entity.Menu;
import com.guciowons.yummify.menu.domain.repository.MenuRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaMenuRepository extends MenuRepository, JpaRepository<Menu, UUID> {
}
