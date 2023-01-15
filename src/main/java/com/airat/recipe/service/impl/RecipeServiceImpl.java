package com.airat.recipe.service.impl;

import com.airat.recipe.model.FileReadErrorException;
import com.airat.recipe.model.FileSaveErrorException;
import com.airat.recipe.model.IncorrectInputException;
import com.airat.recipe.model.Recipe;
import com.airat.recipe.service.RecipeFileService;
import com.airat.recipe.service.RecipeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@EqualsAndHashCode
@Service
public class RecipeServiceImpl implements RecipeService {

    HashMap<Integer, Recipe> recipes = new HashMap<>();
    private final RecipeFileService recipeFileService;

    public RecipeServiceImpl(RecipeFileService recipeFileService) {
        this.recipeFileService = recipeFileService;
    }


    @PostConstruct
    private void init() {
        readFromFile();
    }

    @Override
    public Recipe addRecipe(Recipe recipe) {
        if (recipes.containsKey(recipe.getId())) {
            throw new IncorrectInputException("Такой рецепт уже есть");
        } else {
            recipes.put(recipe.getId(), recipe);
            saveFile();
        }
        return recipe;

    }

    @Override
    public Recipe getRecipe(int id) {
        if (recipes.containsKey(id)) {
            return recipes.get(id);
        } else throw new IncorrectInputException("Такой рецепт не найден");
    }

    @Override
    public Recipe editRecipe(int id, Recipe recipe) {
        if ((recipes.containsKey(id))) {
            saveFile();
            return recipes.put(id, recipe);
        } else throw new IncorrectInputException("Не найден рецепт по ID");
    }


    @Override
    public Recipe removeRecipe(int id) {
        if (recipes.containsKey(id)) {
            return recipes.remove(id);
        } else throw new IncorrectInputException("Не найден рецепт по ID");
    }

    @Override
    public StringBuilder getAllRecipe() {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<Integer, Recipe> recipeEntry : recipes.entrySet()) {
            builder.append(recipeEntry.getKey().toString() + recipeEntry.getValue().toString());
        }
        return builder;
    }

    private void saveFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(recipes);
            recipeFileService.saveToFile(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new FileSaveErrorException("Не удалось сохранить файл");
        }
    }

    private void readFromFile() {
        String json = recipeFileService.readFile();
        try {
            recipes = new ObjectMapper().readValue(json, new TypeReference<HashMap<Integer, Recipe>>() {
                    }
            );
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new FileReadErrorException("Файл не найден");
        }
    }

}
