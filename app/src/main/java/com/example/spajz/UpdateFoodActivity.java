package com.example.spajz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateFoodActivity extends AppCompatActivity {
    private EditText editTextFoodName;
    private EditText editTextFoodCount;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_food);

        // Získání dat potraviny z Intentu
        Intent intent = getIntent();
        String foodName = intent.getStringExtra("foodName");
        int foodCount = intent.getIntExtra("foodCount", 0);
        position = intent.getIntExtra("position", -1);

        // Inicializace pohledů
        editTextFoodName = findViewById(R.id.editTextFoodNameUpdate);
        editTextFoodCount = findViewById(R.id.editTextFoodCountUpdate);
        editTextFoodName.setText(foodName);
        editTextFoodCount.setText(String.valueOf(foodCount));

        Button buttonUpdateFood = findViewById(R.id.buttonSaveChanges);
        buttonUpdateFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateFood();
            }
        });
    }

    private void updateFood() {
        // Získání aktualizovaných hodnot z pohledů
        String updatedFoodName = editTextFoodName.getText().toString();
        int updatedFoodCount = Integer.parseInt(editTextFoodCount.getText().toString());

        // Vytvoření intentu pro přenos výsledků zpět do FoodListActivity
        Intent resultIntent = new Intent();
        resultIntent.putExtra("foodName", updatedFoodName);
        resultIntent.putExtra("foodCount", updatedFoodCount);
        resultIntent.putExtra("position", position);
        setResult(RESULT_OK, resultIntent);
        finish();
    }


}



