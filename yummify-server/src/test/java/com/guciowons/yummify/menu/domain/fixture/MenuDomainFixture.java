package com.guciowons.yummify.menu.domain.fixture;

import com.guciowons.yummify.common.i8n.domain.entity.TranslatedString;
import com.guciowons.yummify.common.i8n.domain.entity.Translation;
import com.guciowons.yummify.common.i8n.domain.enumerated.Language;
import com.guciowons.yummify.menu.domain.entity.MenuEntry;
import com.guciowons.yummify.menu.domain.entity.MenuSection;
import com.guciowons.yummify.menu.domain.entity.MenuVersion;
import com.guciowons.yummify.menu.domain.snapshot.MenuEntrySnapshot;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MenuDomainFixture {
    public static MenuVersion givenMenuVersion(int seed) {
        return new MenuVersion(
                givenMenuVersionId(seed),
                givenMenuVersionRestaurantId(seed),
                seed,
                MenuVersion.Status.DRAFT,
                null
        );
    }

    public static MenuVersion.Id givenMenuVersionId(int seed) {
        return MenuVersion.Id.of(UUID.nameUUIDFromBytes("menu-version-%s".formatted(seed).getBytes()));
    }

    public static MenuVersion.RestaurantId givenMenuVersionRestaurantId(int seed) {
        return MenuVersion.RestaurantId.of(UUID.nameUUIDFromBytes("restaurant-%s".formatted(seed).getBytes()));
    }
    public static MenuSection givenMenuSection(int seed) {
        return new MenuSection(givenMenuSectionId(seed), givenMenuSectionName(seed), seed);
    }

    public static MenuSection.Id givenMenuSectionId(int seed) {
        return MenuSection.Id.of(UUID.nameUUIDFromBytes("menu-section-%s".formatted(seed).getBytes()));
    }

    public static TranslatedString givenMenuSectionName(int seed) {
        return TranslatedString.of(Map.of(Language.EN, Translation.of("menu-section-%s".formatted(seed))));
    }

    public static MenuEntrySnapshot givenNewMenuEntrySnapshot(int seed) {
        return new MenuEntrySnapshot(null, givenMenuEntryDishId(seed), givenMenuEntryPrice(seed));
    }

    public static MenuEntrySnapshot givenExistingMenuEntrySnapshot(int seed) {
        return new MenuEntrySnapshot(givenMenuEntryId(seed), givenMenuEntryDishId(seed), givenMenuEntryPrice(seed));
    }

    public static MenuEntry givenMenuEntry(int seed) {
        return new MenuEntry(givenMenuEntryId(seed), givenMenuEntryDishId(seed), givenMenuEntryPrice(seed));
    }

    public static MenuEntry.Id givenMenuEntryId(int seed) {
        return MenuEntry.Id.of(UUID.nameUUIDFromBytes("menu-entry-%s".formatted(seed).getBytes()));
    }

    public static MenuEntry.DishId givenMenuEntryDishId(int seed) {
        return MenuEntry.DishId.of(UUID.nameUUIDFromBytes("dish-%s".formatted(seed).getBytes()));
    }

    public static MenuEntry.Price givenMenuEntryPrice(int seed) {
        return MenuEntry.Price.of(BigDecimal.valueOf(seed));
    }
}
