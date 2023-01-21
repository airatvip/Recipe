package com.airat.recipe.model;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Ingredient {
    private int id;
    private static int counter = 1;
    private String name;
    private double count;
    private String measureUnit;


    public Ingredient(String name, double count, String measureUnit) {
        this.id = counter++;
        if (name == null || name.isEmpty() || name.isBlank()) {
            throw new IncorrectInputException("Название ингредиентв не может быть пустым. Заполните поле");
        } else
            this.name = name;
        this.count = count;
        if (measureUnit == null || measureUnit.isEmpty() || measureUnit.isBlank()) {
            throw new IncorrectInputException("Заполните поле единицы измерения");
        }
        this.measureUnit = measureUnit;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty() || name.isBlank()) {
            throw new IncorrectInputException("Название ингредиентв не может быть пустым. Заполните поле");
        } else
            this.name = name;
    }

    @Override
    public String toString() {
        return name + " - " + count + " " + measureUnit;

    }
}
