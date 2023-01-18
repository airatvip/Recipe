package com.airat.recipe.service;

import java.io.File;
import java.nio.file.Path;

public interface FileServiceRecipe {
    boolean saveToFile(String json);

    String readFile();

    boolean cleanDataFile();

    File getDataFile();

    Path creatTempFile(String suffix);
}
