package com.guciowons.yummify.file.logic;

import com.guciowons.yummify.common.request.RequestContext;
import com.guciowons.yummify.file.data.FileRepository;
import com.guciowons.yummify.file.entity.File;
import com.guciowons.yummify.file.exception.CannotGetFileException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileStorageService fileStorageService;
    private final FileRepository fileRepository;

    @Transactional
    public void create(String directory, MultipartFile file) {
        UUID restaurantId = RequestContext.get().getUser().getRestaurantId();

        String storageKey = buildStorageKey(file.getOriginalFilename(), restaurantId, directory);
        uploadToStorage(storageKey, file);

        File entity = new File();
        entity.setStorageKey(storageKey);
        entity.setRestaurantId(restaurantId);
        fileRepository.save(entity);
    }

    @Transactional
    public void update(UUID id, String directory, MultipartFile file) {
        UUID restaurantId = RequestContext.get().getUser().getRestaurantId();

        File fileEntity = fileRepository.findByIdAndRestaurantId(id, restaurantId)
                .orElseThrow(RuntimeException::new);

        fileStorageService.deleteFile(fileEntity.getStorageKey());

        String newFileName = buildStorageKey(file.getOriginalFilename(), restaurantId, directory);
        uploadToStorage(newFileName, file);

        fileEntity.setStorageKey(newFileName);
        fileRepository.save(fileEntity);
    }

    @Transactional
    public void delete(UUID id) {
        UUID restaurantId = RequestContext.get().getUser().getRestaurantId();

        File file = fileRepository.findByIdAndRestaurantId(id, restaurantId)
                .orElseThrow(RuntimeException::new);

        fileStorageService.deleteFile(file.getStorageKey());
        fileRepository.delete(file);
    }

    private void uploadToStorage(String storageKey, MultipartFile file) {
        try (InputStream inputStream = file.getInputStream()) {
            fileStorageService.putFile(storageKey, file.getContentType(), inputStream, file.getSize());
        } catch (IOException e) {
            throw new CannotGetFileException();
        }
    }

    private String buildStorageKey(String fileName, UUID restaurantId, String directory) {
        String uniqueName = UUID.randomUUID() + "-" + fileName;
        return String.join("/", restaurantId.toString(), directory, uniqueName);
    }
}
