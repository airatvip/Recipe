package com.airat.recipe.service;

import java.io.File;

public interface FileServiceIngredient {
    boolean saveToFile(String json);

    String readFile();

    boolean cleanDataFile();

    File getDataFile();

}
