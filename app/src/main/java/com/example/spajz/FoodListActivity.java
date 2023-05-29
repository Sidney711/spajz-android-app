/**
 * Author: Zdeněk Carbol, r22430
 */
package com.example.spajz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * FoodListActivity class for showing foods in the food_list_activity
 */
public class FoodListActivity extends AppCompatActivity implements FoodAdapter.OnDeleteClickListener {
    /**
     * Attributes of the class
     */
    private RecyclerView recyclerViewFoodList;
    private Button buttonAddFood;
    private FoodAdapter foodAdapter;
    private List<Food> foodList;
    private FoodManager foodManager;

    /**
     * Method for creating food_list_activity
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Setting view on food_list_activity
        setContentView(R.layout.food_list_activity);

        // Food list area
        recyclerViewFoodList = findViewById(R.id.recyclerViewFoodList);

        // Button for adding food
        buttonAddFood = findViewById(R.id.buttonAddFood);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewFoodList.setLayoutManager(layoutManager);

        foodManager = new FoodManager(this);
        loadFoodList();

        foodAdapter = new FoodAdapter(this, foodList);
        foodAdapter.setOnDeleteClickListener(this);
        recyclerViewFoodList.setAdapter(foodAdapter);

        // Listener for adding food button
        buttonAddFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FoodListActivity.this, AddFoodActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Method for update food list after some changes
     * @param requestCode The integer request code originally supplied to
     *                    startActivityForResult(), allowing you to identify who this
     *                    result came from.
     * @param resultCode The integer result code returned by the child activity
     *                   through its setResult().
     * @param data An Intent, which can return result data to the caller
     *               (various data can be attached to Intent "extras").
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            // Getting position of the food
            int position = data.getIntExtra("position", -1);

            // If the position is not valid
            if (position != -1) {
                String foodName = data.getStringExtra("foodName");
                int foodCount = data.getIntExtra("foodCount", 0);

                // Update food list
                Food updatedFood = foodList.get(position);
                updatedFood.setName(foodName);
                updatedFood.setCount(foodCount);
                foodAdapter.notifyItemChanged(position);

                // Save the new food list
                foodManager.saveFoodList(foodList);
            }
        }
    }

    /**
     * Method for reloading food list after redirection
     */
    @Override
    protected void onResume() {
        super.onResume();
        loadFoodList();
        foodAdapter.setFoodList(foodList);
    }

    /**
     * Method for loading food list
     */
    private void loadFoodList() {
        foodList = foodManager.getFoodList();
    }

    /**
     * Method for deleting food (for calling method in food manager)
     * @param position int position of the food which should be deleted
     */
    private void deleteFood(int position) {
        Food food = foodList.get(position);

        foodManager.deleteFood(food);
        foodList.remove(position);
        foodAdapter.notifyItemRemoved(position);

        // Notification for the user about deleting food
        Toast.makeText(this, "Potravina " + food.getName() + " byla smazána.", Toast.LENGTH_SHORT).show();
    }

    /**
     * Method for the listener of the delete button
     * @param position int position of the food
     */
    @Override
    public void onDeleteClick(int position) {
        deleteFood(position);
    }
}
