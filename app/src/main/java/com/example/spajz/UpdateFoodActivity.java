/**
 * Author: Zdeněk Carbol, r22430
 */
package com.example.spajz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Main class for the activity_update_food
 */
public class UpdateFoodActivity extends AppCompatActivity {
    /**
     * Attributes of the class
     */
    private EditText editTextFoodName;
    private EditText editTextFoodCount;
    private int position;

    /**
     * Method for creating activity_update_food
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_food);

        // Getting foods
        Intent intent = getIntent();
        String foodName = intent.getStringExtra("foodName");
        int foodCount = intent.getIntExtra("foodCount", 0);
        position = intent.getIntExtra("position", -1);

        // Elements initialization
        editTextFoodName = findViewById(R.id.editTextFoodNameUpdate);
        editTextFoodCount = findViewById(R.id.editTextFoodCountUpdate);
        editTextFoodName.setText(foodName);
        editTextFoodCount.setText(String.valueOf(foodCount));

        // Update food listener
        Button buttonUpdateFood = findViewById(R.id.buttonSaveChanges);
        buttonUpdateFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateFood();
            }
        });
    }

    /**
     * Method for updating food
     */
    private void updateFood() {
        // Getting updated values
        String updatedFoodName = editTextFoodName.getText().toString();
        int updatedFoodCount = Integer.parseInt(editTextFoodCount.getText().toString());

        // Returning data back to the foodList
        Intent resultIntent = new Intent();
        resultIntent.putExtra("foodName", updatedFoodName);
        resultIntent.putExtra("foodCount", updatedFoodCount);
        resultIntent.putExtra("position", position);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}
