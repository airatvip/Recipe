package com.airat.recipe.controllers;

import com.airat.recipe.service.FileServiceIngredient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@RequestMapping("/ingredients/files")
@Tag(name = "Ингредиенты")
public class FileIngredientController {
    private final FileServiceIngredient fileServiceIngredient;

    public FileIngredientController(FileServiceIngredient fileServiceIngredient) {
        this.fileServiceIngredient = fileServiceIngredient;
    }


    @GetMapping("/export")
    @Operation(
            summary = "Скачивание ингредиентов"
    )
    public ResponseEntity<InputStreamResource> downloadDataFile() throws FileNotFoundException {
        try {
            File file = fileServiceIngredient.getDataFile();
            if (file.exists() == false) {
                return ResponseEntity.noContent().build();
            }
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.TEXT_PLAIN)
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"Ingredients.txt\"")
                    .body(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.internalServerError().build();
    }


    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(
            summary = "Загрузка ингредиентов"
    )
    public ResponseEntity<Void> uploadDataFile(@RequestParam MultipartFile multipartFile) {
        fileServiceIngredient.cleanDataFile();
        File dataFile = fileServiceIngredient.getDataFile();
        try (FileOutputStream fos = new FileOutputStream(dataFile)) {
            IOUtils.copy(multipartFile.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.internalServerError().build();

    }


}
