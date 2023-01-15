package com.airat.recipe.service;

public interface RecipeFileService {
    boolean saveToFile(String json);

    String readFile();

    boolean cleanDataFile();
}
