package com.guciowons.yummify.table.infrastructure.out.jpa.adapter;

import com.guciowons.yummify.table.infrastructure.out.jpa.entity.JpaTable;
import com.guciowons.yummify.table.infrastructure.out.jpa.entity.mapper.JpaTableMapper;
import com.guciowons.yummify.table.infrastructure.out.jpa.repository.JpaTableRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static com.guciowons.yummify.table.domain.fixture.TableDomainFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class JpaTableRepositoryAdapterTest {
    private final JpaTableRepository jpaTableRepository = mock(JpaTableRepository.class);
    private final JpaTableMapper jpaTableMapper = mock(JpaTableMapper.class);

    private final JpaTableRepositoryAdapter underTest = new JpaTableRepositoryAdapter(
            jpaTableRepository,
            jpaTableMapper
    );

    @Test
    void shouldSaveTable() {
        // given
        var table = givenTable(1);
        var jpaTable = new JpaTable();

        when(jpaTableMapper.toJpa(table)).thenReturn(jpaTable);

        // when
        underTest.save(table);

        // then
        verify(jpaTableMapper).toJpa(table);
        verify(jpaTableRepository).save(jpaTable);
    }

    @Test
    void shouldFindByIdAndRestaurantId() {
        // given
        var tableId = givenTableId(1);
        var restaurantId = givenTableRestaurantId(1);
        var jpaTable = new JpaTable();
        var table = givenTable(1);

        when(jpaTableRepository.findByIdAndRestaurantId(tableId.value(), restaurantId.value()))
                .thenReturn(Optional.of(jpaTable));
        when(jpaTableMapper.toDomain(jpaTable)).thenReturn(table);

        // when
        var result = underTest.findByIdAndRestaurantId(tableId, restaurantId);

        // then
        verify(jpaTableRepository).findByIdAndRestaurantId(tableId.value(), restaurantId.value());
        verify(jpaTableMapper).toDomain(jpaTable);

        assertThat(result).hasValue(table);
    }

    @Test
    void shouldFindAllByRestaurantId() {
        // given
        var restaurantId = givenTableRestaurantId(1);
        var jpaTables = List.of(new JpaTable(), new JpaTable(), new JpaTable());
        var tables = List.of(givenTable(1), givenTable(2), givenTable(3));

        when(jpaTableRepository.findAllByRestaurantId(restaurantId.value())).thenReturn(jpaTables);
        when(jpaTableMapper.toDomain(jpaTables.getFirst())).thenReturn(tables.getFirst());
        when(jpaTableMapper.toDomain(jpaTables.get(1))).thenReturn(tables.get(1));
        when(jpaTableMapper.toDomain(jpaTables.get(2))).thenReturn(tables.get(2));

        // when
        var result = underTest.findAllByRestaurantId(restaurantId);

        // then
        verify(jpaTableRepository).findAllByRestaurantId(restaurantId.value());
        verify(jpaTableMapper).toDomain(jpaTables.getFirst());
        verify(jpaTableMapper).toDomain(jpaTables.get(1));
        verify(jpaTableMapper).toDomain(jpaTables.get(2));

        assertThat(result).isEqualTo(tables);
    }

    @Test
    void shouldReturnExistsByNameAndRestaurantId() {
        // given
        var name = givenTableName(1);
        var restaurantId = givenTableRestaurantId(1);

        // when
        var result = underTest.existsByNameAndRestaurantId(name, restaurantId);

        // then
        verify(jpaTableRepository).existsByNameAndRestaurantId(name.value(), restaurantId.value());

        assertThat(result).isFalse();
    }
}
