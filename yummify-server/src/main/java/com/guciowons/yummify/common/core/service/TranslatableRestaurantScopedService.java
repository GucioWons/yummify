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
        afterSave(dto, savedEntity);
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
        ManageDTO response = mapper.mapToManageDTO(getEntityById(id));
        afterMappingManageDTO(response, getEntityById(id));
        return response;
    }

    public ClientDTO getClientDTO(UUID id) {
        ClientDTO response = mapper.mapToClientDTO(getEntityById(id));
        afterMappingClientDTO(response, getEntityById(id));
        return response;
    }

    @Transactional
    public ManageDTO update(UUID id, ManageDTO dto) {
        validate(dto);
        Entity updatedEntity = mapper.mapToUpdateEntity(dto, getEntityById(id));
        afterMappingEntity(dto, updatedEntity);
        Entity savedEntity = repository.save(updatedEntity);
        afterSave(dto, savedEntity);
        ManageDTO response = mapper.mapToManageDTO(savedEntity);
        afterMappingManageDTO(response, savedEntity);
        return response;
    }

    public Entity getEntityById(UUID id) {
        UUID restaurantId = RequestContext.get().getUser().getRestaurantId();
        return repository.findByIdAndRestaurantId(id, restaurantId)
                .orElseThrow(() -> getNotFoundException(id));
    }

    protected void validate(ManageDTO dto) {}

    protected void afterMappingEntity(ManageDTO dto, Entity entity) {}

    protected void afterSave(ManageDTO dto, Entity entity) {}

    protected void afterMappingManageDTO(ManageDTO dto, Entity entity) {}

    protected void afterMappingClientDTO(ClientDTO dto, Entity entity) {}

    protected abstract SingleApiErrorException getNotFoundException(UUID id);
}
