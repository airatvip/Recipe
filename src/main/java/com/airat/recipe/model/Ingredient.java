package com.airat.recipe.model;

public class Ingredient {
    private int id;
    private static int counter = 1;
    private String name;
    private double count;
    private String measureUnit;



    public Ingredient(String name, double count, String measureUnit) {
        this.id = counter++;
        setName(name);
        this.count = count;
        setMeasureUnit(measureUnit);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty() || name.isBlank()) {
            throw new RuntimeException("Название ингредиентв не может быть пустым. Заполните поле");
        } else
            this.name = name;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public String getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(String measureUnit) {
        if (measureUnit==null || measureUnit.isEmpty() || measureUnit.isBlank()) {
            throw new RuntimeException("Заполните поле единицы измерения");
        }
        this.measureUnit = measureUnit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", measureUnit='" + measureUnit + '\'' +
                '}';
    }
}
