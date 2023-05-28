package com.example.spajz;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ShoppingListActivity extends AppCompatActivity {

    private RecyclerView recyclerViewFoodList;
    private FoodAdapter foodAdapter;
    private List<Food> foodList;
    private FoodManager foodManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);

        recyclerViewFoodList = findViewById(R.id.recyclerViewShoppingList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewFoodList.setLayoutManager(layoutManager);

        foodManager = new FoodManager(this);
        loadFoodList();

        List<Food> filteredFoodList = filterFoodList(foodList);

        foodAdapter = new FoodAdapter(this, filteredFoodList);
        foodAdapter.setShowButtons(true);
        recyclerViewFoodList.setAdapter(foodAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadFoodList();
        List<Food> filteredFoodList = filterFoodList(foodList);
        foodAdapter.setFoodList(filteredFoodList);
    }

    private void loadFoodList() {
        foodList = foodManager.getFoodList();
    }

    private List<Food> filterFoodList(List<Food> foodList) {
        List<Food> filteredList = new ArrayList<>();
        for (Food food : foodList) {
            if (food.getCount() <= 1) {
                filteredList.add(food);
            }
        }
        return filteredList;
    }
}
