package com.guciowons.yummify.menu.infrastructure.out.jpa.entity.mapper;

import com.guciowons.yummify.menu.domain.entity.MenuEntry;
import com.guciowons.yummify.menu.domain.entity.MenuSection;
import com.guciowons.yummify.menu.domain.entity.MenuVersion;
import com.guciowons.yummify.menu.infrastructure.out.jpa.entity.JpaMenuEntry;
import com.guciowons.yummify.menu.infrastructure.out.jpa.entity.JpaMenuSection;
import com.guciowons.yummify.menu.infrastructure.out.jpa.entity.JpaMenuVersion;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface JpaMenuVersionMapper {
    @Mapping(target = "id", expression = "java(MenuVersion.Id.of(jpaMenuVersion.getId()))")
    @Mapping(target = "restaurantId", expression = "java(RestaurantId.of(jpaMenuVersion.getRestaurantId()))")
    MenuVersion toDomain(@MappingTarget JpaMenuVersion jpaMenuVersion);

    @Mapping(target = "id", expression = "java(MenuSection.Id.of(jpaMenuSection.getId()))")
    @Mapping(target = "name", ignore = true)
    MenuSection toDomain(@MappingTarget JpaMenuSection jpaMenuSection);

    @Mapping(target = "id", expression = "java(MenuEntry.Id.of(jpaMenuEntry.getId()))")
    @Mapping(target = "dishId", expression = "java(MenuEntry.DishId.of(jpaMenuEntry.getDishId()))")
    @Mapping(target = "price", expression = "java(MenuEntry.Price.of(jpaMenuEntry.getPrice()))")
    MenuEntry toDomain(@MappingTarget JpaMenuEntry jpaMenuEntry);

    @Mapping(target = "id", source = "id.value")
    @Mapping(target = "restaurantId", source = "restaurantId.value")
    JpaMenuVersion toJpa(@MappingTarget MenuVersion menuVersion);

    @Mapping(target = "id", source = "id.value")
    @Mapping(target = "name", ignore = true)
    JpaMenuSection toJpa(@MappingTarget MenuSection menuSection);

    @Mapping(target = "id", source = "id.value")
    @Mapping(target = "dishId", source = "dishId.value")
    @Mapping(target = "price", source = "price.value")
    JpaMenuEntry toJpa(@MappingTarget MenuEntry menuEntry);

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
