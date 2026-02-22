package com.guciowons.yummify.restaurant.domain.entity;

import com.guciowons.yummify.common.core.domain.entity.IdValueObject;
import com.guciowons.yummify.common.core.domain.entity.ValueObject;
import com.guciowons.yummify.common.i8n.domain.entity.TranslatedString;
import com.guciowons.yummify.common.i8n.domain.enumerated.Language;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class Restaurant {
    private Id id;
    private OwnerId ownerId;
    private Name name;
    private TranslatedString description;
    private Language defaultLanguage;

    public static Restaurant create(Name name, TranslatedString description, Language defaultLanguage) {
        return new Restaurant(Id.random(), null, name, description, defaultLanguage);
    }

    public void changeOwner(OwnerId newOwnerId) {
        this.ownerId = newOwnerId;
    }

    public void updateDetails(Name name, TranslatedString description, Language defaultLanguage) {
        this.name = name;
        this.description = description;
        this.defaultLanguage = defaultLanguage;
    }

    public record Id(UUID value) implements IdValueObject {
        public static Id random() {
            return new Id(UUID.randomUUID());
        }

        public static Id of(UUID value) {
            return new Id(value);
        }
    }

    public record OwnerId(UUID value) implements IdValueObject {
        public static OwnerId of(UUID value) {
            return new OwnerId(value);
        }
    }

    public record Name(String value) implements ValueObject<String> {
        public static Name of(String value) {
            return new Name(value);
        }
    }
}
