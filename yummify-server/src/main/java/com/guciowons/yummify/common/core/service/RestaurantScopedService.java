package com.guciowons.yummify.common.core.service;

import com.guciowons.yummify.common.core.dto.BaseEntityDTO;
import com.guciowons.yummify.common.exception.SingleApiErrorException;
import com.guciowons.yummify.common.request.RequestContext;
import com.guciowons.yummify.common.core.entity.BaseEntity;
import com.guciowons.yummify.common.core.mapper.BaseEntityMapper;
import com.guciowons.yummify.common.core.entity.RestaurantScoped;
import com.guciowons.yummify.common.core.repository.RestaurantScopedRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public abstract class RestaurantScopedService<
        Entity extends BaseEntity & RestaurantScoped,
        DTO extends BaseEntityDTO,
        Repository extends RestaurantScopedRepository<Entity>,
        Mapper extends BaseEntityMapper<DTO, Entity>
        > {

    protected final Repository repository;
    protected final Mapper mapper;

    public List<DTO> getAll() {
        UUID restaurantId = RequestContext.get().getUser().getRestaurantId();
        return repository.findAllByRestaurantId(restaurantId).stream()
                .map(mapper::mapToDTO)
                .toList();
    }

    public DTO getById(UUID id) {
        return mapper.mapToDTO(getEntityById(id));
    }

    @Transactional
    public DTO update(UUID id, DTO dto) {
        Entity updatedEntity = mapper.mapToUpdateEntity(dto, getEntityById(id));
        return mapper.mapToDTO(updatedEntity);
    }

    private Entity getEntityById(UUID id) {
        UUID restaurantId = RequestContext.get().getUser().getRestaurantId();
        return repository.findByIdAndRestaurantId(id, restaurantId)
                .orElseThrow(() -> getNotFoundException(id));
    }

    protected abstract SingleApiErrorException getNotFoundException(UUID id);
}
