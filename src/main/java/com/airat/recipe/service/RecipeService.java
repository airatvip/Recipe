package com.airat.recipe.service;

import com.airat.recipe.model.Recipe;
import org.springframework.stereotype.Service;

import java.nio.file.Path;

@Service
public interface RecipeService {

    Recipe addRecipe(Recipe recipe);

    Recipe getRecipe(int id);

    Recipe editRecipe (int id, Recipe recipe);

    Recipe removeRecipe (int id);

    StringBuilder getAllRecipe ();


    Path createFormatFile();
}
