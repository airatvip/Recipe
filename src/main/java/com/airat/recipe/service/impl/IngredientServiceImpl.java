package com.airat.recipe.service.impl;

import com.airat.recipe.model.IncorrectInputException;
import com.airat.recipe.model.Ingredient;
import com.airat.recipe.service.IngredientService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

@Service
public class IngredientServiceImpl implements IngredientService {

    Map<Integer, Ingredient> ingredients = new TreeMap<>();


    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
        if (ingredients.containsKey(ingredient.getId())) {
            throw new IncorrectInputException("Такой рецепт уже есть");
        } else ingredients.put(ingredient.getId(), ingredient);
        return ingredient;
    }

    @Override
    public Ingredient getIngredient(int id) {
        if (ingredients.containsKey(id)) {
            return ingredients.get(id);
        } else throw new IncorrectInputException ("Нет таких ингредиентов");
    }

    @Override
    public Ingredient editIngredient(int id, Ingredient ingredient) {
        if ((ingredients.containsKey(id))) {
            return ingredients.put(id, ingredient);
        } else throw new IncorrectInputException ("Не найден ингредиент по ID");
    }

    @Override
    public Ingredient removeIngredient(int id) {
        if (ingredients.containsKey(id)) {
            return ingredients.remove(id);
        } else throw new IncorrectInputException ("Не найден ингредиент по ID");
    }


    @Override
    public StringBuilder getAllIngredient() {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<Integer, Ingredient> ingredientEntry : ingredients.entrySet()) {
            builder.append(ingredientEntry.getKey().toString() + ingredientEntry.getValue().toString());
        }
        return builder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IngredientServiceImpl that = (IngredientServiceImpl) o;
        return Objects.equals(ingredients, that.ingredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredients);
    }


}
