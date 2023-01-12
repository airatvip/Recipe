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
    @PutMapping("/{id}")
    public Recipe editRecipe (@PathVariable int id, @RequestBody Recipe recipe) {
        return recipeService.editRecipe(id, recipe);
    }
    @DeleteMapping("/{id}")
    public Recipe removeRecipe (@PathVariable int id) {
        return recipeService.removeRecipe(id);
    }
    @GetMapping("/all")
    public StringBuilder getAllRecipe () {
        return recipeService.getAllRecipe();
    }


}
