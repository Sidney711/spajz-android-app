package com.example.spajz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddFoodActivity extends AppCompatActivity {

    private Button buttonAddFood;
    private FoodManager foodManager;
    private EditText editTextFoodName;
    private EditText editTextFoodCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_food_activity);

        buttonAddFood = findViewById(R.id.buttonAddFood);
        editTextFoodName = findViewById(R.id.editTextFoodName);
        editTextFoodCount = findViewById(R.id.editTextFoodCount);

        foodManager = new FoodManager(this);

        buttonAddFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFood();
            }
        });
    }

    private void addFood() {
        String foodName = editTextFoodName.getText().toString().trim();
        int foodCount = Integer.parseInt(editTextFoodCount.getText().toString().trim());

        foodManager.addFood(foodName, foodCount);
        foodManager.saveFoodListToJson(getBaseContext());

        Toast.makeText(AddFoodActivity.this, "Potravina přidána", Toast.LENGTH_SHORT).show();

        Intent resultIntent = new Intent();
        setResult(RESULT_OK, resultIntent);
        finish();
    }

}
