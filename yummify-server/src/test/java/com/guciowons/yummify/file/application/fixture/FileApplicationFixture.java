package com.guciowons.yummify.file.application.fixture;

import com.guciowons.yummify.file.application.model.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.InputStream;

import static com.guciowons.yummify.file.domain.fixture.FileDomainFixture.*;
import static org.mockito.Mockito.mock;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileApplicationFixture {
    public static CreateFileCommand givenCreateFileCommand() {
        return new CreateFileCommand(givenDirectory(1), givenFileContent(1), givenFileRestaurantId(1));
    }

    public static DeleteFileCommand givenDeleteFileCommand() {
        return new DeleteFileCommand(givenFileId(1), givenFileRestaurantId(1));
    }

    public static GetFileUrlCommand givenGetFileUrlCommand() {
        return new GetFileUrlCommand(givenFileId(1), givenFileRestaurantId(1));
    }

    public static UpdateFileCommand givenUpdateFileCommand() {
        return new UpdateFileCommand(givenFileId(1), givenDirectory(1), givenFileContent(1), givenFileRestaurantId(1));
    }

    public static FileContent givenFileContent(int seed) {
        return new FileContent(
                givenFilename(seed),
                "content-type",
                mock(InputStream.class),
                Integer.toUnsignedLong(seed)
        );
    }
}
