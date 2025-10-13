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
        Entity extends BaseEntity & RestaurantScoped,
        ManageDTO extends BaseEntityDTO,
        ClientDTO extends BaseEntityDTO,
        ListDTO extends BaseEntityDTO>
{
    protected final RestaurantScopedRepository<Entity> repository;
    protected final TranslatableMapper<Entity, ManageDTO, ClientDTO, ListDTO> mapper;

    @Transactional
    public ManageDTO create(ManageDTO dto) {
        validate(dto);
        Entity entity = mapper.mapToSaveEntity(dto);
        entity.setRestaurantId(RequestContext.get().getUser().getRestaurantId());
        afterMappingEntity(dto, entity);
        Entity savedEntity = repository.save(entity);
        ManageDTO response = mapper.mapToManageDTO(savedEntity);
        afterMappingManageDTO(response, savedEntity);
        return response;
    }

    public List<ListDTO> getAll() {
        UUID restaurantId = RequestContext.get().getUser().getRestaurantId();
        return repository.findAllByRestaurantId(restaurantId).stream()
                .map(mapper::mapToListDTO)
                .toList();
    }

    public ManageDTO getManageDTO(UUID id) {
        return mapper.mapToManageDTO(getEntityById(id));
    }

    public ClientDTO getClientDTO(UUID id) {
        return mapper.mapToClientDTO(getEntityById(id));
    }

    @Transactional
    public ManageDTO update(UUID id, ManageDTO dto) {
        validate(dto);
        Entity updatedEntity = mapper.mapToUpdateEntity(dto, getEntityById(id));
        afterMappingEntity(dto, updatedEntity);
        Entity savedEntity = repository.save(updatedEntity);
        ManageDTO response = mapper.mapToManageDTO(savedEntity);
        afterMappingManageDTO(response, savedEntity);
        return response;
    }

    protected Entity getEntityById(UUID id) {
        UUID restaurantId = RequestContext.get().getUser().getRestaurantId();
        return repository.findByIdAndRestaurantId(id, restaurantId)
                .orElseThrow(() -> getNotFoundException(id));
    }

    protected void validate(ManageDTO dto) {

    }

    protected void afterMappingEntity(ManageDTO dto, Entity entity) {

    }

    protected void afterMappingManageDTO(ManageDTO dto, Entity entity) {

    }

    protected abstract SingleApiErrorException getNotFoundException(UUID id);
}
