package com.example.helper.files.zip;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static com.example.helper.logger.MessageFormatter.format;
import static java.util.Objects.requireNonNull;
import static org.assertj.core.api.Assertions.fail;

public class Zipper {

    /**
     * Архивировать каталог со всеми файлами
     *
     * @param sourceDirPath - путь к каталогу
     * @param zipFilePath   - путь к расположению файла архива
     */
    public static void archiveDirectoryToZip(String sourceDirPath, String zipFilePath) {
        Path newZipFilePath = null;

        try {
            newZipFilePath = Files.createFile(Paths.get(zipFilePath));
        } catch (IOException e) {
            String failMessage = format("IOException - Error by createFile \"{}\"\n{}",
                    zipFilePath,
                    e.getMessage()
            );
            fail(failMessage);
        }

        try (ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(requireNonNull(newZipFilePath)))) {
            Path dirPath = Paths.get(sourceDirPath);
            Files.walk(dirPath)
                    .filter(path -> !Files.isDirectory(path))
                    .forEach(path -> {
                        ZipEntry zipEntry = new ZipEntry(dirPath.relativize(path).toString());
                        try {
                            zipOutputStream.putNextEntry(zipEntry);
                            Files.copy(path, zipOutputStream);
                            zipOutputStream.closeEntry();
                        } catch (IOException e) {
                            String failMessage = format("IOException - Error by zipping:\n\tdirectory - {}\n\tarray - {}\n{}",
                                    sourceDirPath,
                                    zipFilePath,
                                    e.getMessage()
                            );
                            fail(failMessage);
                        }
                    });
        } catch (IOException e) {
            String failMessage = format("IOException - Error by zipping:\n\tdirectory - {}\n\tarray - {}\n{}",
                    sourceDirPath,
                    zipFilePath,
                    e.getMessage()
            );
            fail(failMessage);
        }
    }
}
