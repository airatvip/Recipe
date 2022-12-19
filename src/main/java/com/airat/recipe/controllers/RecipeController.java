package com.airat.recipe.controllers;

import com.airat.recipe.model.Recipe;
import com.airat.recipe.service.impl.RecipeServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    private final RecipeServiceImpl recipeService;


    public RecipeController(RecipeServiceImpl recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/{id}")
    public Recipe getRecipe(@PathVariable int id) {
        return recipeService.getRecipe(id);
    }
    @PostMapping("/add")
    public Recipe addRecipe (@RequestBody Recipe recipe) {
        return recipeService.addRecipe(recipe);
    }


}
