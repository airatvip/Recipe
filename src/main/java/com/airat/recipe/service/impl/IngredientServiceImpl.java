package com.airat.recipe.service.impl;

import com.airat.recipe.model.IncorrectInputException;
import com.airat.recipe.model.Ingredient;
import com.airat.recipe.service.IngredientFileService;
import com.airat.recipe.service.IngredientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.TreeMap;

@EqualsAndHashCode
@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientFileService ingredientfileService;

    TreeMap<Integer, Ingredient> ingredients = new TreeMap<>();

    public IngredientServiceImpl(IngredientFileService ingredientfileService) {
        this.ingredientfileService = ingredientfileService;
    }


    @PostConstruct
    private void init() {
        readFromFile();
    }
    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
        if (ingredients.containsKey(ingredient.getId())) {
            throw new IncorrectInputException("Такой рецепт уже есть");
        } else ingredients.put(ingredient.getId(), ingredient);
        saveFile();
        return ingredient;
    }

    @Override
    public Ingredient getIngredient(int id) {
        if (ingredients.containsKey(id)) {
            saveFile();
            return ingredients.get(id);
        } else throw new IncorrectInputException("Нет таких ингредиентов");
    }

    @Override
    public Ingredient editIngredient(int id, Ingredient ingredient) {
        if ((ingredients.containsKey(id))) {
            return ingredients.put(id, ingredient);
        } else throw new IncorrectInputException("Не найден ингредиент по ID");
    }

    @Override
    public Ingredient removeIngredient(int id) {
        if (ingredients.containsKey(id)) {
            return ingredients.remove(id);
        } else throw new IncorrectInputException("Не найден ингредиент по ID");
    }


    @Override
    public StringBuilder getAllIngredient() {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<Integer, Ingredient> ingredientEntry : ingredients.entrySet()) {
            builder.append(ingredientEntry.getKey().toString() + ingredientEntry.getValue().toString());
        }
        return builder;
    }

    private void saveFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(ingredients);
            ingredientfileService.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFromFile() {
        String json = ingredientfileService.readFile();
        try {
            ingredients = new ObjectMapper().readValue(json, new TypeReference<TreeMap<Integer, Ingredient>>() {
                    }
            );
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


}
