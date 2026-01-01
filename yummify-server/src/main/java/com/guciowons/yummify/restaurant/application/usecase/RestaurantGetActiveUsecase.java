package com.guciowons.yummify.restaurant.application.usecase;

import com.guciowons.yummify.common.request.RequestContext;
import com.guciowons.yummify.restaurant.domain.port.RestaurantRepositoryPort;
import com.guciowons.yummify.restaurant.domain.entity.Restaurant;
import com.guciowons.yummify.restaurant.exception.RestaurantNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RestaurantGetActiveUsecase {
    private final RestaurantRepositoryPort restaurantRepositoryPort;

    public Restaurant get() {
        UUID id = RequestContext.get().getUser().getRestaurantId();
        return restaurantRepositoryPort.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));
    }
}
