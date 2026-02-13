package com.guciowons.yummify.menu.infrastructure.out.jpa.repository;

import com.guciowons.yummify.menu.domain.entity.MenuVersion;
import com.guciowons.yummify.menu.infrastructure.out.jpa.entity.JpaMenuVersion;
import com.guciowons.yummify.menu.infrastructure.out.jpa.entity.mapper.JpaMenuVersionMapper;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static com.guciowons.yummify.menu.domain.fixture.MenuDomainFixture.givenMenuVersion;
import static com.guciowons.yummify.menu.domain.fixture.MenuDomainFixture.givenMenuVersionRestaurantId;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class JpaMenuVersionRepositoryAdapterTest {
    private final JpaMenuVersionMapper jpaMenuVersionMapper = mock(JpaMenuVersionMapper.class);
    private final JpaMenuVersionRepository jpaMenuVersionRepository = mock(JpaMenuVersionRepository.class);

    private final JpaMenuVersionRepositoryAdapter underTest = new JpaMenuVersionRepositoryAdapter(
            jpaMenuVersionMapper,
            jpaMenuVersionRepository
    );

    @Test
    void shouldSaveMenuVersion() {
        // given
        var menuVersion = givenMenuVersion(1);
        var jpaMenuVersion = new JpaMenuVersion();

        when(jpaMenuVersionMapper.toJpa(menuVersion)).thenReturn(jpaMenuVersion);

        // when
        underTest.save(menuVersion);

        // then
        verify(jpaMenuVersionMapper).toJpa(menuVersion);
        verify(jpaMenuVersionRepository).save(jpaMenuVersion);
    }

    @Test
    void shouldFindDraftByRestaurantId() {
        // given
        var menuVersion = givenMenuVersion(1);
        var restaurantId = menuVersion.getRestaurantId();
        var status = MenuVersion.Status.DRAFT.name();
        var jpaMenuVersion = new JpaMenuVersion();

        when(jpaMenuVersionRepository.findByRestaurantIdAndStatus(restaurantId.value(), status))
                .thenReturn(Optional.of(jpaMenuVersion));
        when(jpaMenuVersionMapper.toDomain(jpaMenuVersion)).thenReturn(menuVersion);

        // when
        var result = underTest.findDraftByRestaurantId(restaurantId);

        // then
        verify(jpaMenuVersionRepository).findByRestaurantIdAndStatus(restaurantId.value(), status);
        verify(jpaMenuVersionMapper).toDomain(jpaMenuVersion);

        assertThat(result).hasValue(menuVersion);
    }

    @Test
    void shouldFindPublishedByRestaurantId() {
        // given
        var menuVersion = givenMenuVersion(1);
        var restaurantId = menuVersion.getRestaurantId();
        var status = MenuVersion.Status.PUBLISHED.name();
        var jpaMenuVersion = new JpaMenuVersion();

        when(jpaMenuVersionRepository.findByRestaurantIdAndStatus(restaurantId.value(), status))
                .thenReturn(Optional.of(jpaMenuVersion));
        when(jpaMenuVersionMapper.toDomain(jpaMenuVersion)).thenReturn(menuVersion);

        // when
        var result = underTest.findPublishedByRestaurantId(restaurantId);

        // then
        verify(jpaMenuVersionRepository).findByRestaurantIdAndStatus(restaurantId.value(), status);
        verify(jpaMenuVersionMapper).toDomain(jpaMenuVersion);

        assertThat(result).hasValue(menuVersion);
    }

    @Test
    void findAllByRestaurantId() {
        // given
        var restaurantId = givenMenuVersionRestaurantId(1);
        var jpaMenuVersions = List.of(new JpaMenuVersion(), new JpaMenuVersion(), new JpaMenuVersion());
        var menuVersions = List.of(givenMenuVersion(1), givenMenuVersion(2), givenMenuVersion(3));

        when(jpaMenuVersionRepository.findAllByRestaurantIdAndStatus(restaurantId.value(), "ARCHIVED"))
                .thenReturn(jpaMenuVersions);
        when(jpaMenuVersionMapper.toDomain(jpaMenuVersions.getFirst())).thenReturn(menuVersions.getFirst());
        when(jpaMenuVersionMapper.toDomain(jpaMenuVersions.get(1))).thenReturn(menuVersions.get(1));
        when(jpaMenuVersionMapper.toDomain(jpaMenuVersions.get(2))).thenReturn(menuVersions.get(2));

        // when
        var result = underTest.findAllArchivedByRestaurantId(restaurantId);

        // then
        verify(jpaMenuVersionRepository).findAllByRestaurantIdAndStatus(restaurantId.value(), "ARCHIVED");
        verify(jpaMenuVersionMapper).toDomain(jpaMenuVersions.getFirst());
        verify(jpaMenuVersionMapper).toDomain(jpaMenuVersions.get(1));
        verify(jpaMenuVersionMapper).toDomain(jpaMenuVersions.get(2));

        assertThat(result).isEqualTo(menuVersions);
    }

    @Test
    void existsByRestaurantId() {
        // given
        var restaurantId = givenMenuVersionRestaurantId(1);

        // when
        var result = underTest.existsByRestaurantId(restaurantId);

        // then
        verify(jpaMenuVersionRepository).existsByRestaurantId(restaurantId.value());

        assertThat(result).isFalse();
    }
}