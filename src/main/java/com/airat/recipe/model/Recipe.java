package com.airat.recipe.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;

@Data
@NoArgsConstructor

public class Recipe {

    private static int count = 1;
    private int id;
    private String name;
    private String cookingTime;
    private LinkedList<Ingredient> ingredients;
    private LinkedList<String> steps;

    public Recipe(String name, String cookingTime, LinkedList ingredients, LinkedList steps) {
        this.id = count++;
        if (name == null || name.isEmpty() || name.isBlank()) {
            throw new IncorrectInputException("Название рецепта не может быть пустым. Заполните поле");
        } else
            this.name = name;
        if (cookingTime == null || cookingTime.isEmpty() || cookingTime.isBlank()) {
            throw new IncorrectInputException("Заполните поле \"Время приготовления\"");
        }
        this.cookingTime = cookingTime;
        ;
        if (ingredients == null) {
            throw new IncorrectInputException("Заполните ингредиенты");
        }
        this.ingredients = ingredients;
        this.steps = steps;
    }

    public StringBuilder ingredientsBuilder() {
        StringBuilder ingredientString = new StringBuilder();
        for (int i = 0; i < getIngredients().size(); i++) {
            ingredientString.append(ingredients.get(i).getName() + " " +
                    (ingredients.get(i).getCount() == 0.0 ? "" : ingredients.get(i).getCount()) + " " +
                    ingredients.get(i).getMeasureUnit() +
                    '\n');
        }
        return ingredientString;

    }

    public StringBuilder stepsBuilder() {
        StringBuilder stepString = new StringBuilder();
        for (Object step : steps) {
            stepString.append(step + "\n");
        }
        return stepString;


    }
}
