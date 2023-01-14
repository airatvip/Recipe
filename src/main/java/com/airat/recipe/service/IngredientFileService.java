package com.airat.recipe.service;

public interface IngredientFileService {
    boolean saveToFile(String json);

    String readFile();

    boolean cleanDataFile();
}
