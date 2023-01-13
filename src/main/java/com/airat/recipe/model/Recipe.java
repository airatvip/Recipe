package com.airat.recipe.model;

import lombok.Getter;
import lombok.ToString;

import java.util.LinkedList;

@Getter
@ToString

public class Recipe {

    private static int count = 1;
    private int id;
    private String name;
    private String cookingTime;
    private LinkedList ingredients;
    private LinkedList steps;

    public Recipe(String name, String cookingTime, LinkedList ingredients, LinkedList steps) {
        this.id = count++;
        setName(name);
        setCookingTime(cookingTime);
        setIngredients(ingredients);
        this.steps = steps;
    }


    public static void setCount(int count) {
        Recipe.count = count;
    }


    public void setId(int id) {
        this.id = id;
    }


    public void setName(String name) {
        if (name == null || name.isEmpty() || name.isBlank()) {
            throw new IncorrectInputException("Название рецепта не может быть пустым. Заполните поле");
        } else
            this.name = name;
    }


    public void setCookingTime(String cookingTime) {
        if (cookingTime == null || cookingTime.isEmpty() || cookingTime.isBlank()) {
            throw new IncorrectInputException("Заполните поле \"Время приготовления\"");
        }
        this.cookingTime = cookingTime;
    }


    public void setIngredients(LinkedList ingredients) {
        if (ingredients == null) {
            throw new IncorrectInputException("Заполните ингредиенты");
        }
        this.ingredients = ingredients;
    }

    public LinkedList getSteps() {
        return steps;
    }

    public void setStep(LinkedList steps) {
        this.steps = steps;
    }

}
