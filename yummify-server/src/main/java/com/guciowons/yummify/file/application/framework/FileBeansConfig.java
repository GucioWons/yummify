package com.guciowons.yummify.file.application.framework;

import com.guciowons.yummify.common.RestaurantScopedService;
import com.guciowons.yummify.file.domain.entity.File;
import com.guciowons.yummify.file.domain.exception.FileNotFoundException;
import com.guciowons.yummify.file.domain.port.FileRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileBeansConfig {
    @Bean
    public RestaurantScopedService<File> fileRestaurantScopedService(FileRepositoryPort fileRepositoryPort) {
        return new RestaurantScopedService<>(
                fileRepositoryPort,
                FileNotFoundException::new
        );
    }
}
