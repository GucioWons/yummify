package com.guciowons.yummify.menu.infrastructure.out.jpa.entity.mapper;

import com.guciowons.yummify.common.i8n.infrastructure.jpa.JpaTranslatedStringMapper;
import com.guciowons.yummify.menu.domain.entity.MenuEntry;
import com.guciowons.yummify.menu.domain.entity.MenuSection;
import com.guciowons.yummify.menu.domain.entity.MenuVersion;
import com.guciowons.yummify.menu.infrastructure.out.jpa.entity.JpaMenuEntry;
import com.guciowons.yummify.menu.infrastructure.out.jpa.entity.JpaMenuSection;
import com.guciowons.yummify.menu.infrastructure.out.jpa.entity.JpaMenuVersion;
import org.mapstruct.*;

import java.math.BigDecimal;
import java.util.UUID;

@Mapper(
        componentModel = "spring",
        uses = JpaTranslatedStringMapper.class,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface JpaMenuVersionMapper {
    MenuVersion toDomain(JpaMenuVersion jpaMenuVersion);

    MenuSection toDomain(JpaMenuSection jpaMenuSection);

    MenuEntry toDomain(JpaMenuEntry jpaMenuEntry);

    @Mapping(target = "id", source = "id.value")
    @Mapping(target = "restaurantId", source = "restaurantId.value")
    JpaMenuVersion toJpa(MenuVersion menuVersion);

    @Mapping(target = "id", source = "id.value")
    @Mapping(target = "version", ignore = true)
    JpaMenuSection toJpa(MenuSection menuSection);

    @Mapping(target = "id", source = "id.value")
    @Mapping(target = "dishId", source = "dishId.value")
    @Mapping(target = "price", source = "price.value")
    @Mapping(target = "section", ignore = true)
    JpaMenuEntry toJpa(MenuEntry menuEntry);

    default MenuVersion.Id toMenuVersionId(UUID id) {
        return MenuVersion.Id.of(id);
    }

    default MenuSection.Id toMenuSectionId(UUID id) {
        return MenuSection.Id.of(id);
    }

    default MenuEntry.Id toMenuEntryId(UUID id) {
        return MenuEntry.Id.of(id);
    }

    default MenuVersion.RestaurantId toRestaurantId(UUID id) {
        return MenuVersion.RestaurantId.of(id);
    }

    default MenuEntry.DishId toDishId(UUID dishId) {
        return MenuEntry.DishId.of(dishId);
    }

    default MenuEntry.Price toPrice(BigDecimal price) {
        return MenuEntry.Price.of(price);
    }

    @AfterMapping
    default void afterMapping(@MappingTarget JpaMenuVersion jpaMenuVersion) {
        jpaMenuVersion.getSections()
                .forEach(jpaMenuSection -> jpaMenuSection.setVersion(jpaMenuVersion));
    }

    @AfterMapping
    default void afterMapping(@MappingTarget JpaMenuSection jpaMenuSection) {
        jpaMenuSection.getEntries()
                .forEach(jpaMenuEntry -> jpaMenuEntry.setSection(jpaMenuSection));
    }
}
