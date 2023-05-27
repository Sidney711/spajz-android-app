package com.example.spajz;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FoodManager {
    private static final String SHARED_PREFS_NAME = "FoodManagerPrefs";
    private static final String FOOD_LIST_KEY = "FoodListKey";

    private Context context;
    private SharedPreferences sharedPreferences;
    private Gson gson;

    public FoodManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    public List<Food> getFoodList() {
        List<Food> foodList;
        String json = sharedPreferences.getString(FOOD_LIST_KEY, null);
        if (json != null) {
            Type type = new TypeToken<List<Food>>() {}.getType();
            foodList = gson.fromJson(json, type);
        } else {
            foodList = new ArrayList<>();
        }
        return foodList;
    }

    public void addFood(Food food) {
        List<Food> foodList = getFoodList();
        foodList.add(food);
        saveFoodList(foodList);
    }

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

    public void saveFoodList(List<Food> foodList) {
        String json = gson.toJson(foodList);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(FOOD_LIST_KEY, json);
        editor.apply();
    }

}
