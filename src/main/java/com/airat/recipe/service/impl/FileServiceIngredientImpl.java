package com.airat.recipe.service.impl;

import com.airat.recipe.model.FileReadErrorException;
import com.airat.recipe.service.FileServiceIngredient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileServiceIngredientImpl implements FileServiceIngredient {
    @Value("${path.to.data.files}")
    private String dataFilePath;
    @Value("${nameIngredient.of.data.files}")
    private String nameDataFile;

    @Override
    public boolean saveToFile(String json) {
        try {
            cleanDataFile();
            Files.writeString(Path.of(dataFilePath, nameDataFile), json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String readFile() {
        try {
            return Files.readString(Path.of(dataFilePath, nameDataFile));

        } catch (IOException e) {
            e.printStackTrace();
            throw new FileReadErrorException("Ошибка при чтении файла");
        }

    }

    @Override
    public boolean cleanDataFile() {
        Path path = Path.of(dataFilePath, nameDataFile);
        try {
            Files.delete(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public File getDataFile() {
        return new File(dataFilePath + "/" + nameDataFile);
    }

}
