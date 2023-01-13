package com.airat.recipe.model;

import java.util.LinkedList;


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

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Recipe.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty() || name.isBlank()) {
            throw new RuntimeException("Название рецепта не может быть пустым. Заполните поле");
        } else
            this.name = name;
    }

    public String getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(String cookingTime) {
        if (cookingTime==null || cookingTime.isEmpty() || cookingTime.isBlank()) {
            throw new RuntimeException("Заполните поле \"Время приготовления\"");
        }
            this.cookingTime = cookingTime;
    }

    public LinkedList getIngredients() {
        return ingredients;
    }

    public void setIngredients(LinkedList ingredients) {
        if (ingredients == null) {
            throw new RuntimeException("Заполните ингредиенты");
        }
        this.ingredients = ingredients;
    }

    public LinkedList getSteps() {
        return steps;
    }

    public void setStep(LinkedList steps) {
        this.steps = steps;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cookingTime='" + cookingTime + '\'' +
                ", ingredients=" + ingredients +
                ", steps=" + steps +
                '}';
    }
}
