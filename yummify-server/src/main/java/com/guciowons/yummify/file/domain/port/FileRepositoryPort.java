package com.guciowons.yummify.file.domain.port;

import com.guciowons.yummify.common.core.repository.RestaurantScopedRepository;
import com.guciowons.yummify.file.domain.entity.File;

public interface FileRepositoryPort extends RestaurantScopedRepository<File> {
}
