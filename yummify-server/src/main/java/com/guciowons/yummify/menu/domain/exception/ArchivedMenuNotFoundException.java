package com.guciowons.yummify.menu.domain.exception;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.menu.domain.entity.MenuVersion;
import lombok.Getter;

@Getter
public class ArchivedMenuNotFoundException extends DomainException {
    private final MenuVersion.Id id;

    public ArchivedMenuNotFoundException(MenuVersion.Id id) {
        super("Archived menu not found by id");
        this.id = id;
    }
}
