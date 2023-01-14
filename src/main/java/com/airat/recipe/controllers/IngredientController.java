package com.airat.recipe.controllers;


import com.airat.recipe.model.Ingredient;
import com.airat.recipe.service.IngredientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredients")
@Tag(name = "Ингредиенты", description = "Операции над ингредиентами")
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }
    @Operation(
            summary = "Получить ингредиент по id",
            description = "Введите номер id ингредиента чтобы получить его"
    )
    @GetMapping("/{id}")
    public Ingredient getIngredients(@PathVariable int id) {
        return ingredientService.getIngredient(id);
    }
    @Operation(
            summary = "Добавить новый ингредиент",
            description = "Введите новый ингредиент в формате JSON"
    )
    @PostMapping()
    public Ingredient addIngredients(@RequestBody Ingredient ingredient) {
        return ingredientService.addIngredient(ingredient);
    }
    @Operation(
            summary = "Редактировать ингредиент по id",
            description = "Введите id ингредиента и отредактированный ингредиент в формате JSON"
    )
    @PutMapping("/{id}")
    public Ingredient editIngredient(@PathVariable int id, @RequestBody Ingredient ingredient) {
        return ingredientService.editIngredient(id, ingredient);
    }
    @Operation(
            summary = "Удаление ингредиента по id",
            description = "Введите id ингредиента для удаления"
    )
    @DeleteMapping("/{id}")
    public Ingredient removeIngredient(@PathVariable int id) {
        return ingredientService.removeIngredient(id);
    }
    @Operation(
            summary = "Получение всех ингредиентов"
        )
    @GetMapping()
    public StringBuilder getAllIngredients() {
        return ingredientService.getAllIngredient();
    }
}
