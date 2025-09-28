package com.guciowons.yummify.common.core.service;

import com.guciowons.yummify.common.core.dto.BaseEntityDTO;
import com.guciowons.yummify.common.exception.SingleApiErrorException;
import com.guciowons.yummify.common.request.RequestContext;
import com.guciowons.yummify.common.core.entity.BaseEntity;
import com.guciowons.yummify.common.core.entity.RestaurantScoped;
import com.guciowons.yummify.common.core.repository.RestaurantScopedRepository;
import com.guciowons.yummify.common.core.mapper.TranslatableMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public abstract class TranslatableRestaurantScopedService<
        Entity extends BaseEntity & RestaurantScoped, DTO extends BaseEntityDTO, ManageDTO extends DTO, ClientDTO extends DTO,
        Repository extends RestaurantScopedRepository<Entity>,
        Mapper extends TranslatableMapper<Entity, DTO, ManageDTO, ClientDTO>
        > {

    protected final Repository repository;
    protected final Mapper mapper;

    @Transactional
    public ManageDTO create(ManageDTO dto) {
        Entity entity = mapper.mapToSaveEntity(dto);
        entity.setRestaurantId(RequestContext.get().getUser().getRestaurantId());
        return mapper.mapToManageDTO(repository.save(entity));
    }

    public List<ClientDTO> getAll() {
        UUID restaurantId = RequestContext.get().getUser().getRestaurantId();
        return repository.findAllByRestaurantId(restaurantId).stream()
                .map(mapper::mapToClientDTO)
                .toList();
    }

    public ManageDTO getById(UUID id) {
        return mapper.mapToManageDTO(getEntityById(id));
    }

    @Transactional
    public ManageDTO update(UUID id, ManageDTO dto) {
        Entity updatedEntity = mapper.mapToUpdateEntity(dto, getEntityById(id));
        return mapper.mapToManageDTO(updatedEntity);
    }

    private Entity getEntityById(UUID id) {
        UUID restaurantId = RequestContext.get().getUser().getRestaurantId();
        return repository.findByIdAndRestaurantId(id, restaurantId)
                .orElseThrow(() -> getNotFoundException(id));
    }

    protected abstract SingleApiErrorException getNotFoundException(UUID id);
}
