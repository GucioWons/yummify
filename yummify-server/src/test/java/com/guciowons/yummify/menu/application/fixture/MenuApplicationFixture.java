package com.guciowons.yummify.menu.application.fixture;

import com.guciowons.yummify.menu.application.entry.model.CreateMenuEntryCommand;
import com.guciowons.yummify.menu.application.entry.model.UpdateMenuEntryCommand;
import com.guciowons.yummify.menu.application.section.model.CreateMenuSectionCommand;
import com.guciowons.yummify.menu.application.section.model.UpdateMenuSectionNameCommand;
import com.guciowons.yummify.menu.application.section.model.UpdateMenuSectionPositionCommand;
import com.guciowons.yummify.menu.application.version.model.*;
import com.guciowons.yummify.menu.domain.entity.MenuSection;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

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

    public static GetArchivedMenuVersionQuery givenGetArchivedMenuVersionQuery() {
        return new GetArchivedMenuVersionQuery(givenMenuVersionId(1), givenMenuVersionRestaurantId(1));
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

    public static RestoreMenuVersionCommand givenRestoreMenuVersionCommand() {
        return new RestoreMenuVersionCommand(givenMenuVersionId(1), givenMenuVersionRestaurantId(1));
    }

    public static CreateMenuEntryCommand givenCreateMenuEntryCommand(MenuSection.Id sectionId) {
        return new CreateMenuEntryCommand(
                sectionId,
                givenMenuVersionRestaurantId(1),
                givenMenuEntryDishId(1),
                givenMenuEntryPrice(1)
        );
    }

    public static UpdateMenuEntryCommand givenUpdateMenuEntryCommand(MenuSection.Id sectionId) {
        return new UpdateMenuEntryCommand(
                sectionId,
                givenMenuEntryId(1),
                givenMenuVersionRestaurantId(1),
                givenMenuEntryDishId(1),
                givenMenuEntryPrice(1)
        );
    }
}
