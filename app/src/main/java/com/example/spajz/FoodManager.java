package com.example.spajz;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FoodManager {
    private List<Food> foodList;

    public FoodManager(Context context) {
        loadFoodList(context);
    }

    public List<Food> loadFoodList(Context context) {
        try {
            InputStream inputStream = context.openFileInput("food_list.json");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            Gson gson = new Gson();
            Type type = new TypeToken<List<Food>>() {}.getType();
            List<Food> loadedFoodList = gson.fromJson(inputStreamReader, type);
            inputStream.close();

            if (loadedFoodList != null) {
                foodList = loadedFoodList;
            } else {
                foodList = new ArrayList<>();
            }
        } catch (IOException e) {
            e.printStackTrace();
            foodList = new ArrayList<>();
        }

        return foodList;
    }


    private void saveFoodList(Context context) {
        try {
            OutputStream outputStream = context.openFileOutput("food_list.json", Context.MODE_PRIVATE);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            Gson gson = new Gson();
            gson.toJson(foodList, outputStreamWriter);
            outputStreamWriter.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addFood(String foodName, int foodCount) {
        Food food = new Food(foodName, foodCount);
        foodList.add(food);
    }

    public List<Food> getFoodList() {
        return foodList;
    }

    public void saveFoodListToJson(Context context) {
        saveFoodList(context);
    }
}
