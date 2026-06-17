package com.guciowons.yummify.order.infrastructure.out.jpa.entity;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "order_item", schema = "order")
public class JpaOrderItem {
    @Id
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private JpaOrder order;

    @Column(nullable = false)
    private UUID dishId;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb", nullable = false)
    private Map<String, String> nameSnapshot;

    @Column(nullable = false)
    private BigDecimal price;
}
