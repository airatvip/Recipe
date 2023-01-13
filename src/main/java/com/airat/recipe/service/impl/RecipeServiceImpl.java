package com.airat.recipe.service.impl;

import com.airat.recipe.model.IncorrectInputException;
import com.airat.recipe.model.Recipe;
import com.airat.recipe.service.RecipeService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class RecipeServiceImpl implements RecipeService {

    Map<Integer, Recipe> recipes = new HashMap<>();

    @Override
    public Recipe addRecipe(Recipe recipe) throws IncorrectInputException {
        if (recipes.containsKey(recipe.getId())) {
            throw new IncorrectInputException("Такой рецепт уже есть");
        } else {
            recipes.put(recipe.getId(), recipe);
        }
        return recipe;

    }

    @Override
    public Recipe getRecipe(int id) {
        if (recipes.containsKey(id)) {
            return recipes.get(id);
        } else throw new IncorrectInputException("Такой рецепт не найден");
    }

    @Override
    public Recipe editRecipe(int id, Recipe recipe) {
        if ((recipes.containsKey(id))) {
            return recipes.put(id, recipe);
        } else throw new IncorrectInputException("Не найден рецепт по ID");
    }


    @Override
    public Recipe removeRecipe(int id) {
        if (recipes.containsKey(id)) {
            return recipes.remove(id);
        } else throw new IncorrectInputException("Не найден рецепт по ID");
    }

    @Override
    public StringBuilder getAllRecipe() {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<Integer, Recipe> recipeEntry : recipes.entrySet()) {
            builder.append(recipeEntry.getKey().toString() + recipeEntry.getValue().toString());
        }
        return builder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeServiceImpl that = (RecipeServiceImpl) o;
        return Objects.equals(recipes, that.recipes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipes);
    }
}
