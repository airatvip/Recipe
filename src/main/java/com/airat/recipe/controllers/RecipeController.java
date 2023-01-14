package com.airat.recipe.controllers;

import com.airat.recipe.model.Recipe;
import com.airat.recipe.service.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recipe")
@Tag(name = "Рецепты", description = "Операции с рецептами")
public class RecipeController {

    private final RecipeService recipeService;


    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }
@Operation (summary = "Получение рецепта по id",
description = "Введите номер id рецепта чтобы получить его")
    @GetMapping("/{id}")
    public Recipe getRecipe(@PathVariable int id) {
        return recipeService.getRecipe(id);
    }
    @Operation(
            summary = "Добавить новый рецепт",
            description = "Введите новый рецепт в формате JSON"
    )
    @PostMapping()
    public Recipe addRecipe (@RequestBody Recipe recipe) {
        return recipeService.addRecipe(recipe);
    }
    @Operation(
            summary = "Редактировать рецепт по id",
            description = "Введите id рецепта и отредактированный рецепт в формате JSON"
    )
    @PutMapping("/{id}")
    public Recipe editRecipe (@PathVariable int id, @RequestBody Recipe recipe) {
        return recipeService.editRecipe(id, recipe);
    }
    @Operation(
            summary = "Удаление рецепта по id",
            description = "Введите id рецепта для удаления"
    )
    @DeleteMapping("/{id}")
    public Recipe removeRecipe (@PathVariable int id) {
        return recipeService.removeRecipe(id);
    }
    @Operation(
            summary = "Получение всех рецептов"
    )
    @GetMapping()
    public StringBuilder getAllRecipe () {
        return recipeService.getAllRecipe();
    }


}
