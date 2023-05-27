package com.example.spajz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddFoodActivity extends AppCompatActivity {

    private EditText editTextFoodName;
    private EditText editTextFoodCount;
    private Button buttonAddFood;

    private FoodManager foodManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_food_activity);

        foodManager = new FoodManager(this);

        editTextFoodName = findViewById(R.id.editTextFoodName);
        editTextFoodCount = findViewById(R.id.editTextFoodCount);
        buttonAddFood = findViewById(R.id.buttonAddFood);

        buttonAddFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFood();
            }
        });
    }

    private void addFood() {
        String foodName = editTextFoodName.getText().toString().trim();
        String foodCountText = editTextFoodCount.getText().toString().trim();

        if (foodName.isEmpty() || foodCountText.isEmpty()) {
            Toast.makeText(this, "Vyplňte prosím všechna pole", Toast.LENGTH_SHORT).show();
            return;
        }

        int foodCount = Integer.parseInt(foodCountText);
        Food food = new Food(foodName, foodCount);
        foodManager.addFood(food);

        Toast.makeText(this, "Potravina přidána", Toast.LENGTH_SHORT).show();

        Intent resultIntent = new Intent();
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}
