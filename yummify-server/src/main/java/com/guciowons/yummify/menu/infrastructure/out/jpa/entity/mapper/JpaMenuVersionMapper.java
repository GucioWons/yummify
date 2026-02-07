package com.guciowons.yummify.menu.infrastructure.out.jpa.entity.mapper;

import com.guciowons.yummify.common.i8n.infrastructure.jpa.JpaTranslatedStringMapper;
import com.guciowons.yummify.menu.domain.entity.MenuEntry;
import com.guciowons.yummify.menu.domain.entity.MenuSection;
import com.guciowons.yummify.menu.domain.entity.MenuVersion;
import com.guciowons.yummify.menu.infrastructure.out.jpa.entity.JpaMenuEntry;
import com.guciowons.yummify.menu.infrastructure.out.jpa.entity.JpaMenuSection;
import com.guciowons.yummify.menu.infrastructure.out.jpa.entity.JpaMenuVersion;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = JpaTranslatedStringMapper.class)
public interface JpaMenuVersionMapper {
    @Mapping(target = "id", expression = "java(MenuVersion.Id.of(jpaMenuVersion.getId()))")
    @Mapping(target = "restaurantId", expression = "java(RestaurantId.of(jpaMenuVersion.getRestaurantId()))")
    MenuVersion toDomain(JpaMenuVersion jpaMenuVersion);

    @Mapping(target = "id", expression = "java(MenuSection.Id.of(jpaMenuSection.getId()))")
    MenuSection toDomain(JpaMenuSection jpaMenuSection);

    @Mapping(target = "id", expression = "java(MenuEntry.Id.of(jpaMenuEntry.getId()))")
    @Mapping(target = "dishId", expression = "java(MenuEntry.DishId.of(jpaMenuEntry.getDishId()))")
    @Mapping(target = "price", expression = "java(MenuEntry.Price.of(jpaMenuEntry.getPrice()))")
    MenuEntry toDomain(JpaMenuEntry jpaMenuEntry);

    @Mapping(target = "id", source = "id.value")
    @Mapping(target = "restaurantId", source = "restaurantId.value")
    JpaMenuVersion toJpa(MenuVersion menuVersion);

    @Mapping(target = "id", source = "id.value")
    JpaMenuSection toJpa(MenuSection menuSection);

    @Mapping(target = "id", source = "id.value")
    @Mapping(target = "dishId", source = "dishId.value")
    @Mapping(target = "price", source = "price.value")
    JpaMenuEntry toJpa(MenuEntry menuEntry);

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
