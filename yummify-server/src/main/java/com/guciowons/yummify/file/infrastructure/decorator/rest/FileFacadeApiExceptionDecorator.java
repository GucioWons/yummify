package com.guciowons.yummify.file.infrastructure.decorator.rest;

import com.guciowons.yummify.common.exception.infrastructure.in.rest.annotation.HandleDomainExceptions;
import com.guciowons.yummify.file.FileFacadePort;
import com.guciowons.yummify.file.infrastructure.in.rest.exception.FileDomainExceptionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.util.UUID;

@RequiredArgsConstructor
public class FileFacadeApiExceptionDecorator implements FileFacadePort {
    private final FileFacadePort delegate;

    @Override
    @HandleDomainExceptions(handler = FileDomainExceptionMapper.class)
    public UUID create(String directory, MultipartFile file, UUID restaurantId) {
        return delegate.create(directory, file, restaurantId);
    }

    @Override
    @HandleDomainExceptions(handler = FileDomainExceptionMapper.class)
    public void update(UUID id, String directory, MultipartFile file, UUID restaurantId) {
        delegate.update(id, directory, file, restaurantId);
    }

    @Override
    @HandleDomainExceptions(handler = FileDomainExceptionMapper.class)
    public void delete(UUID id, UUID restaurantId) {
        delegate.delete(id, restaurantId);
    }

    @Override
    @HandleDomainExceptions(handler = FileDomainExceptionMapper.class)
    public URL getUrl(UUID id, UUID restaurantId) {
        return delegate.getUrl(id, restaurantId);
    }
}
