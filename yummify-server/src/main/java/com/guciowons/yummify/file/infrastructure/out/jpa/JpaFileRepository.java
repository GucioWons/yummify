package com.guciowons.yummify.file.infrastructure.out.jpa;

import com.guciowons.yummify.file.domain.entity.File;
import com.guciowons.yummify.file.domain.port.out.FileRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaFileRepository extends FileRepository, JpaRepository<File, UUID> {
}
