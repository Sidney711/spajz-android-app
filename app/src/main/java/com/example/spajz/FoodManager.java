/**
 * Author: Zdeněk Carbol, r22430
 */
package com.example.spajz;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for working with data (JSON file for storing data)
 * It works with SharedPreferences for storing data
 * It stores data in JSON format
 * For working with JSON data Gson library is used there
 */
public class FoodManager {
    /**
     * Attributes of the class
     */
    // Information for storing data
    private static final String SHARED_PREFS_NAME = "FoodManagerPrefs";
    private static final String FOOD_LIST_KEY = "FoodListKey";

    private final Context context;
    private final SharedPreferences sharedPreferences;
    private final Gson gson;

    /**
     * Easy constructor for the FoodManager
     * @param context context
     */
    public FoodManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    /**
     * Method which gets foods from the JSON
     * @return List<Food>
     */
    public List<Food> getFoodList() {
        List<Food> foodList;
        String json = sharedPreferences.getString(FOOD_LIST_KEY, null);

        if (json != null) {
            Type type = new TypeToken<List<Food>>() {
            }.getType();
            foodList = gson.fromJson(json, type);
        } else {
            foodList = new ArrayList<>();
        }

        return foodList;
    }

    /**
     * Method for adding food to the JSON
     * @param food Food food which will be added to the JSON
     */
    public void addFood(Food food) {
        List<Food> foodList = getFoodList();

        foodList.add(food);
        saveFoodList(foodList);
    }

    /**
     * Method for deleting the food from the JSON
     * @param food Food food which will be deleted
     */
    public void deleteFood(Food food) {
        List<Food> foodList = getFoodList();

        for (int i = 0; i < foodList.size(); i++) {
            Food f = foodList.get(i);
            if (f.getName().equals(food.getName()) && f.getCount() == food.getCount()) {
                foodList.remove(i);
                break;
            }
        }

        saveFoodList(foodList);
    }

    /**
     * Method for saving food list to the JSON
     * @param foodList List<Food>
     */
    public void saveFoodList(List<Food> foodList) {
        String json = gson.toJson(foodList);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(FOOD_LIST_KEY, json);
        editor.apply();
    }
}
