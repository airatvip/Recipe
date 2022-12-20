package com.airat.recipe.service;

import com.airat.recipe.model.Recipe;
import org.springframework.stereotype.Service;

@Service
public interface RecipeService {

    Recipe addRecipe(Recipe recipe);

    Recipe getRecipe(int id);


}
