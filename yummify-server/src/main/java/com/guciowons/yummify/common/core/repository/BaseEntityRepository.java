package com.guciowons.yummify.common.core.repository;

import com.guciowons.yummify.common.core.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BaseEntityRepository<Entity extends BaseEntity> extends JpaRepository<Entity, UUID> {
}
