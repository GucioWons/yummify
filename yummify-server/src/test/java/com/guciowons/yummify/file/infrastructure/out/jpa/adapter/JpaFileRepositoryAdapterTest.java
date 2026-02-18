package com.guciowons.yummify.file.infrastructure.out.jpa.adapter;

import com.guciowons.yummify.file.infrastructure.out.jpa.entity.JpaFile;
import com.guciowons.yummify.file.infrastructure.out.jpa.entity.mapper.JpaFileMapper;
import com.guciowons.yummify.file.infrastructure.out.jpa.repository.JpaFileRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.guciowons.yummify.file.domain.fixture.FileDomainFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class JpaFileRepositoryAdapterTest {
    private final JpaFileRepository jpaFileRepository = mock(JpaFileRepository.class);
    private final JpaFileMapper jpaFileMapper = mock(JpaFileMapper.class);

    private final JpaFileRepositoryAdapter underTest = new JpaFileRepositoryAdapter(jpaFileRepository, jpaFileMapper);

    @Test
    void shouldSaveFile() {
        // given
        var file = givenFile(1);
        var jpaFile = new JpaFile();

        when(jpaFileMapper.toJpa(file)).thenReturn(jpaFile);

        // when
        underTest.save(file);

        // then
        verify(jpaFileMapper).toJpa(file);
        verify(jpaFileRepository).save(jpaFile);
    }

    @Test
    void shouldFindFile() {
        // given
        var id = givenFileId(1);
        var restaurantId = givenFileRestaurantId(1);
        var jpaFile = new JpaFile();
        var file = givenFile(1);

        when(jpaFileRepository.findByIdAndRestaurantId(id.value(), restaurantId.value()))
                .thenReturn(Optional.of(jpaFile));
        when(jpaFileMapper.toDomain(jpaFile)).thenReturn(file);

        // when
        var result = underTest.findByIdAndRestaurantId(id, restaurantId);

        // then
        verify(jpaFileRepository).findByIdAndRestaurantId(id.value(), restaurantId.value());
        verify(jpaFileMapper).toDomain(jpaFile);

        assertThat(result).hasValue(file);
    }
}