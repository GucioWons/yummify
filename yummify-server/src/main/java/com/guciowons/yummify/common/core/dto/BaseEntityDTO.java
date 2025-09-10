package com.guciowons.yummify.common.core.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public abstract class BaseEntityDTO {
    private UUID id;
}
