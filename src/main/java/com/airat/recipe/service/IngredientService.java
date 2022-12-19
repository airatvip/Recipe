package com.airat.recipe.service;

import com.airat.recipe.model.Ingredient;

public interface IngredientService {

    Ingredient addIngredient(Ingredient ingredient);

    Ingredient getIngredient(int id);
}
