package com.guciowons.yummify.common.security.enumerated;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRole {
    ADMIN(null),
    OWNER(ADMIN);

    private final UserRole parent;

    public boolean hasRole(UserRole requiredRole) {
        UserRole current = this;
        while (current != null) {
            if (current == requiredRole) {
                return true;
            }
            current = current.getParent();
        }
        return false;
    }
}
