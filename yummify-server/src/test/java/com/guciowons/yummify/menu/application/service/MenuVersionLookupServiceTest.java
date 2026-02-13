package com.guciowons.yummify.menu.application.service;

import com.guciowons.yummify.menu.domain.exception.ArchivedMenuNotFoundException;
import com.guciowons.yummify.menu.domain.exception.DraftMenuVersionNotFoundException;
import com.guciowons.yummify.menu.domain.exception.PublishedMenuVersionNotFoundException;
import com.guciowons.yummify.menu.domain.port.out.MenuVersionRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.guciowons.yummify.menu.domain.fixture.MenuDomainFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class MenuVersionLookupServiceTest {
    private final MenuVersionRepository menuVersionRepository = mock(MenuVersionRepository.class);

    private final MenuVersionLookupService underTest = new MenuVersionLookupService(menuVersionRepository);

    @Test
    void shouldGetDraftMenuVersion_WhenDraftExists() {
        // given
        var restaurantId = givenMenuVersionRestaurantId(1);
        var menuVersion = givenMenuVersion(1);

        when(menuVersionRepository.findDraftByRestaurantId(restaurantId)).thenReturn(Optional.of(menuVersion));

        // when
        var result = underTest.getDraftByRestaurantId(restaurantId);

        // then
        verify(menuVersionRepository).findDraftByRestaurantId(restaurantId);

        assertThat(result).isEqualTo(menuVersion);
    }

    @Test
    void shouldThrowException_WhenDraftNotExists() {
        // given
        var restaurantId = givenMenuVersionRestaurantId(1);

        when(menuVersionRepository.findDraftByRestaurantId(restaurantId)).thenReturn(Optional.empty());

        // when
        assertThatThrownBy(() -> underTest.getDraftByRestaurantId(restaurantId))
                .isInstanceOf(DraftMenuVersionNotFoundException.class);

        // then
        verify(menuVersionRepository).findDraftByRestaurantId(restaurantId);
    }

    @Test
    void shouldGetPublishedMenuVersion_WhenPublishedExists() {
        // given
        var restaurantId = givenMenuVersionRestaurantId(1);
        var menuVersion = givenMenuVersion(1);

        when(menuVersionRepository.findPublishedByRestaurantId(restaurantId)).thenReturn(Optional.of(menuVersion));

        // when
        var result = underTest.getPublishedByRestaurantId(restaurantId);

        // then
        verify(menuVersionRepository).findPublishedByRestaurantId(restaurantId);

        assertThat(result).isEqualTo(menuVersion);
    }

    @Test
    void shouldThrowException_WhenPublishedNotExists() {
        // given
        var restaurantId = givenMenuVersionRestaurantId(1);

        when(menuVersionRepository.findPublishedByRestaurantId(restaurantId)).thenReturn(Optional.empty());

        // when
        assertThatThrownBy(() -> underTest.getPublishedByRestaurantId(restaurantId))
                .isInstanceOf(PublishedMenuVersionNotFoundException.class);

        // then
        verify(menuVersionRepository).findPublishedByRestaurantId(restaurantId);
    }

    @Test
    void shouldGetArchivedMenuVersion_WhenArchivedExists() {
        // given
        var id = givenMenuVersionId(1);
        var restaurantId = givenMenuVersionRestaurantId(1);
        var menuVersion = givenMenuVersion(1);

        when(menuVersionRepository.findArchivedByIdAndRestaurantId(id, restaurantId))
                .thenReturn(Optional.of(menuVersion));

        // when
        var result = underTest.getArchivedByIdAndRestaurantId(id, restaurantId);

        // then
        verify(menuVersionRepository).findArchivedByIdAndRestaurantId(id, restaurantId);

        assertThat(result).isEqualTo(menuVersion);
    }

    @Test
    void shouldThrowException_WhenArchivedNotExists() {
        // given
        var id = givenMenuVersionId(1);
        var restaurantId = givenMenuVersionRestaurantId(1);

        when(menuVersionRepository.findArchivedByIdAndRestaurantId(id, restaurantId)).thenReturn(Optional.empty());

        // when
        assertThatThrownBy(() -> underTest.getArchivedByIdAndRestaurantId(id, restaurantId))
                .isInstanceOf(ArchivedMenuNotFoundException.class);

        // then
        verify(menuVersionRepository).findArchivedByIdAndRestaurantId(id, restaurantId);
    }
}
