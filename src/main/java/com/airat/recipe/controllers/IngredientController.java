package com.airat.recipe.controllers;


import com.airat.recipe.model.Ingredient;
import com.airat.recipe.service.IngredientService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/{id}")
    public Ingredient getIngredients(@PathVariable int id) {
        return ingredientService.getIngredient(id);
    }

    @PostMapping("/add")
    public Ingredient addIngredients(@RequestBody Ingredient ingredient) {
        return ingredientService.addIngredient(ingredient);
    }

    @PutMapping("/{id}")
    public Ingredient editIngredient(@PathVariable int id, @RequestBody Ingredient ingredient) {
        return ingredientService.editIngredient(id, ingredient);
    }

    @DeleteMapping("/{id}")
    public Ingredient removeIngredient(@PathVariable int id) {
        return ingredientService.removeIngredient(id);
    }

    @GetMapping("/all")
    public StringBuilder getAllIngredients() {
        return ingredientService.getAllIngredient();
    }
}
