package com.guciowons.yummify.file.data;

import com.guciowons.yummify.common.core.repository.RestaurantScopedRepository;
import com.guciowons.yummify.file.entity.File;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends RestaurantScopedRepository<File> {
}
