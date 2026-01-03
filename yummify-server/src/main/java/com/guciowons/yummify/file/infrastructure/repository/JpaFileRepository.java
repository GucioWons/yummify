package com.guciowons.yummify.file.infrastructure.repository;

import com.guciowons.yummify.file.domain.entity.File;
import com.guciowons.yummify.file.domain.repository.FileRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaFileRepository extends FileRepository, JpaRepository<File, UUID> {
}
