package com.guciowons.yummify.common.core.domain.entity;

import java.util.UUID;

public interface BaseEntity {
    UUID getId();
    void setId(UUID id);
}
