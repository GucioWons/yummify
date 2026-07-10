package com.guciowons.yummify.auth.infrastructure.out.jpa.entity;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "role", schema = "auth")
public class JpaRole {
    @Id
    private UUID id;

    @Column(nullable = false)
    private UUID restaurantId;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb", nullable = false)
    private Map<String, String> name;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb", nullable = false)
    private Set<String> permissions;
}
