package com.guciowons.yummify.file.infrastructure.out.jpa.adapter;

import com.guciowons.yummify.file.domain.entity.File;
import com.guciowons.yummify.file.domain.port.out.FileRepository;
import com.guciowons.yummify.file.infrastructure.out.jpa.entity.mapper.JpaFileMapper;
import com.guciowons.yummify.file.infrastructure.out.jpa.repository.JpaFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JpaFileRepositoryAdapter implements FileRepository {
    private final JpaFileRepository jpaFileRepository;
    private final JpaFileMapper jpaFileMapper;

    @Override
    public void save(File file) {
        jpaFileRepository.save(jpaFileMapper.toJpa(file));
    }

    @Override
    public void delete(File file) {
        jpaFileRepository.delete(jpaFileMapper.toJpa(file));
    }

    @Override
    public Optional<File> findByIdAndRestaurantId(File.Id id, File.RestaurantId restaurantId) {
        return jpaFileRepository.findByIdAndRestaurantId(id.value(), restaurantId.value())
                .map(jpaFileMapper::toDomain);
    }
}
