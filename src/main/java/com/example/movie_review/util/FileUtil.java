package com.example.movie_review.util;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Slf4j
@Component
public class FileUtil {
    private static final String UPLOAD_DIR = "data/";

    @SneakyThrows // using printStackTrace
    public String saveUploadedFile(MultipartFile file, String subDir) {
        String uuidFile = UUID.randomUUID().toString();
        String resultFileName = String.format("%s_%s", uuidFile, file.getOriginalFilename());

        Path pathDir = Paths.get(UPLOAD_DIR + subDir);
        Files.createDirectories(pathDir);

        Path filePath = Paths.get(String.format("%s/%s", pathDir, resultFileName));
        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
        }
        try (OutputStream outputStream = Files.newOutputStream(filePath)) {
            outputStream.write(file.getBytes());
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return resultFileName;
    }
}
