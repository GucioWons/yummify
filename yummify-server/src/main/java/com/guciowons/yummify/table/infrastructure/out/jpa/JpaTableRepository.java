package com.guciowons.yummify.table.infrastructure.out.jpa;

import com.guciowons.yummify.table.domain.entity.Table;
import com.guciowons.yummify.table.domain.repository.TableRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaTableRepository extends TableRepository, JpaRepository<Table, UUID> {
}
