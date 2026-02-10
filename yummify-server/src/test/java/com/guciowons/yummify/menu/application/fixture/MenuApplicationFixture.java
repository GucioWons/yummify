package com.guciowons.yummify.menu.application.fixture;

import com.guciowons.yummify.menu.application.model.*;
import com.guciowons.yummify.menu.domain.entity.MenuSection;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.guciowons.yummify.menu.domain.fixture.MenuDomainFixture.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MenuApplicationFixture {
    public static CreateMenuSectionCommand givenCreateMenuSectionCommand() {
        return new CreateMenuSectionCommand(givenMenuVersionRestaurantId(1), givenMenuSectionName(1));
    }

    public static CreateMenuVersionCommand givenCreateMenuVersionCommand() {
        return new CreateMenuVersionCommand(givenMenuVersionRestaurantId(1));
    }

    public static GetMenuVersionQuery givenGetMenuVersionQuery() {
        return new GetMenuVersionQuery(givenMenuVersionRestaurantId(1));
    }

    public static UpdateMenuSectionEntriesCommand givenUpdateMenuSectionEntriesCommand(MenuSection.Id sectionId) {
        return new UpdateMenuSectionEntriesCommand(
                sectionId,
                givenMenuVersionRestaurantId(1),
                List.of(givenNewMenuEntrySnapshot(1))
        );
    }

    public static UpdateMenuSectionNameCommand givenUpdateMenuSectionNameCommand(MenuSection.Id sectionId) {
        return new UpdateMenuSectionNameCommand(
                sectionId,
                givenMenuVersionRestaurantId(1),
                givenMenuSectionName(1)
        );
    }

    public  static UpdateMenuSectionPositionCommand givenUpdateMenuSectionPositionCommand(MenuSection.Id sectionId) {
        return new UpdateMenuSectionPositionCommand(sectionId, givenMenuVersionRestaurantId(1), 1);
    }

    public static PublishMenuVersionCommand givenPublishMenuVersionCommand() {
        return new PublishMenuVersionCommand(givenMenuVersionRestaurantId(1));
    }
}
