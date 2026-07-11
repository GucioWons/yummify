package com.guciowons.yummify.auth.domain.model;

import com.guciowons.yummify.common.core.domain.entity.IdValueObject;
import com.guciowons.yummify.common.i8n.domain.entity.TranslatedString;
import com.guciowons.yummify.common.security.domain.Permission;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class Role {
    private Id id;
    private RestaurantId restaurantId;
    private TranslatedString name;
    private Set<Permission> permissions;

    public static Role create(RestaurantId restaurantId, TranslatedString name, Set<Permission> permissions) {
        return new Role(Id.random(), restaurantId, name, permissions);
    }

    public record Id(UUID value) implements IdValueObject {
        public static Id random() {
            return new Id(UUID.randomUUID());
        }

        public static Id of(UUID value) {
            return new Id(value);
        }
    }

    public record RestaurantId(UUID value) implements IdValueObject {
        public static RestaurantId of(UUID value) {
            return new RestaurantId(value);
        }
    }
}
