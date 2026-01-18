package com.guciowons.yummify.menu.infrastructure.out.persistence;

import com.guciowons.yummify.menu.domain.entity.Menu;
import com.guciowons.yummify.menu.domain.port.out.MenuRepositoryPort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaMenuRepository extends MenuRepositoryPort, JpaRepository<Menu, UUID> {
}
