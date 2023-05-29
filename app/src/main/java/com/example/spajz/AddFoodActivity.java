/**
 * Author: Zdeněk Carbol, r22430
 */
package com.example.spajz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Activity class for adding new food to food list
 */
public class AddFoodActivity extends AppCompatActivity {
    /**
     * Attributes of the AddFoodActivity
     */
    private EditText editTextFoodName;
    private EditText editTextFoodCount;
    private Button buttonAddFood;
    private FoodManager foodManager;

    /**
     * Method onCreate
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Setting layout too add_food_activity
        setContentView(R.layout.add_food_activity);

        // Reference on new object of food manager class
        foodManager = new FoodManager(this);

        // Setting text inputs and button for adding food
        editTextFoodName = findViewById(R.id.editTextFoodName);
        editTextFoodCount = findViewById(R.id.editTextFoodCount);
        buttonAddFood = findViewById(R.id.buttonAddFood);

        // Listener for buttonAddFood, if user will click on the button, food will be added with
        // addFood method from this class
        buttonAddFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFood();
            }
        });
    }

    /**
     * Method for adding food to food list, it is step before real adding to the list
     * with foodManager
     */
    private void addFood() {
        // Getting data from the user (name of the food & count)
        String foodName = editTextFoodName.getText().toString().trim();
        String foodCountText = editTextFoodCount.getText().toString().trim();

        // Empty check
        if (foodName.isEmpty() || foodCountText.isEmpty()) {
            Toast.makeText(this, "Vyplňte prosím všechna pole", Toast.LENGTH_SHORT).show();
            return;
        }

        // Parse food to int from string
        int foodCount = Integer.parseInt(foodCountText);

        // Create reference on new object of the Food class
        Food food = new Food(foodName, foodCount);

        // Real adding food to the food list with food manager
        foodManager.addFood(food);

        // Info for the user about adding food to the list
        Toast.makeText(this, "Potravina přidána", Toast.LENGTH_SHORT).show();

        // Setting result & finishing activity
        Intent resultIntent = new Intent();
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}
