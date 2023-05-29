/**
 * Author: Zdeněk Carbol, r22430
 */
package com.example.spajz;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Main class for setting shopping list
 */
public class ShoppingListActivity extends AppCompatActivity {
    /**
     * Attributes of the class
     */
    private RecyclerView recyclerViewFoodList;
    private FoodAdapter foodAdapter;
    private List<Food> foodList;
    private FoodManager foodManager;

    /**
     * Method for creating activity_shopping_list
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     */
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
        foodAdapter.setHideButtons(true);
        recyclerViewFoodList.setAdapter(foodAdapter);
    }

    /**
     * On reload list method
     */
    @Override
    protected void onResume() {
        super.onResume();

        loadFoodList();
        List<Food> filteredFoodList = filterFoodList(foodList);
        foodAdapter.setFoodList(filteredFoodList);
    }

    /**
     * Method for loading food list
     */
    private void loadFoodList() {
        foodList = foodManager.getFoodList();
    }

    /**
     * Method for filtering food list for shopping list
     * All foods with count <= 1 are returned to the shopping list
     * @param foodList List<Food> foodList
     * @return List<Food>
     */
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
