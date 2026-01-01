package com.guciowons.yummify.table.application.framework;

import com.guciowons.yummify.common.RestaurantScopedService;
import com.guciowons.yummify.table.domain.provider.TableRepositoryProvider;
import com.guciowons.yummify.table.domain.entity.Table;
import com.guciowons.yummify.table.exception.TableNotFoundException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TableBeansConfig {
    @Bean
    public RestaurantScopedService<Table> tableRestaurantScopedService(
            TableRepositoryProvider tableRepositoryProvider
    ) {
        return new RestaurantScopedService<>(
                tableRepositoryProvider,
                TableNotFoundException::new
        );
    }
}
