package com.guciowons.yummify.table.infrastructure.out.jpa.entity.mapper;

import com.guciowons.yummify.common.i8n.infrastructure.jpa.JpaTranslatedStringMapper;
import com.guciowons.yummify.table.domain.entity.Table;
import com.guciowons.yummify.table.infrastructure.out.jpa.entity.JpaTable;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = JpaTranslatedStringMapper.class
)
public interface JpaTableMapper {
    Table toDomain(JpaTable jpaTable);

    @Mapping(target = "id", source = "id.value")
    @Mapping(target = "restaurantId", source = "restaurantId.value")
    @Mapping(target = "userId", source = "userId.value")
    @Mapping(target = "name", source = "name.value")
    @Mapping(target = "capacity", source = "capacity.value")
    JpaTable toJpa(Table table);

    default Table.Id toId(UUID id) {
        return Table.Id.of(id);
    }

    default Table.RestaurantId toRestaurantId(UUID id) {
        return Table.RestaurantId.of(id);
    }

    default Table.UserId toUserId(UUID id) {
        return Table.UserId.of(id);
    }

    default Table.Name toName(String name) {
        return Table.Name.of(name);
    }

    default Table.Capacity toCapacity(int capacity) {
        return Table.Capacity.of(capacity);
    }
}
