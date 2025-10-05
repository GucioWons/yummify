package com.guciowons.yummify.menu.entity;

import com.guciowons.yummify.common.core.entity.BaseEntity;
import com.guciowons.yummify.common.i8n.TranslatedString;
import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class MenuSection implements BaseEntity {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private Menu menu;

    private Integer position;

    @Type(JsonBinaryType.class)
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private TranslatedString name;

    @OneToMany
    private List<MenuEntry> entries;
}
