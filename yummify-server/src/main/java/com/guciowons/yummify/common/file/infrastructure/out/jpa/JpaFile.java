package com.guciowons.yummify.common.file.infrastructure.out.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public class JpaFile {
    @Id
    private UUID id;

    @Column(nullable = false)
    private String storageKey;
}
