package com.guciowons.yummify.file.domain.repository;

import com.guciowons.yummify.common.core.domain.repository.RestaurantScopedRepository;
import com.guciowons.yummify.file.domain.entity.File;

public interface FileRepository extends RestaurantScopedRepository<File> {
    void delete(File file);
}
