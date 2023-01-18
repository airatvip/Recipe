package com.airat.recipe.controllers;

import com.airat.recipe.service.FileServiceRecipe;
import com.airat.recipe.service.RecipeService;
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
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("/recipe/files")
@Tag(name = "Рецепты")
public class FileRecipeController {

    private final FileServiceRecipe fileServiceRecipe;
    private final RecipeService recipeService;

    public FileRecipeController(FileServiceRecipe fileServiceRecipe, RecipeService recipeService) {
        this.fileServiceRecipe = fileServiceRecipe;
        this.recipeService = recipeService;
    }

    @GetMapping("/export")
    @Operation(
            summary = "Скачивание рецептов"
    )
    public ResponseEntity<InputStreamResource> downloadTextFile() throws FileNotFoundException {
        try {
            Path path = recipeService.createFormatFile();
            if (Files.size(path) == 0) {
                return ResponseEntity.noContent().build();
            }
            InputStreamResource resource = new InputStreamResource(new FileInputStream(path.toFile()));
            return ResponseEntity.ok()
                    .contentType(MediaType.TEXT_PLAIN)
                    .contentLength(Files.size(path))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"Recipe.txt\"")
                    .body(resource);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }


    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(
            summary = "Загрузка рецептов"
    )
    public ResponseEntity<Void> uploadDataFile(@RequestParam MultipartFile multipartFile) {
        fileServiceRecipe.cleanDataFile();
        File dataFile = fileServiceRecipe.getDataFile();
        try (FileOutputStream fos = new FileOutputStream(dataFile)) {
            IOUtils.copy(multipartFile.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.internalServerError().build();

    }
}

