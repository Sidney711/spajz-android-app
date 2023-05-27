package com.example.spajz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

public class FoodListActivity extends AppCompatActivity {
    private RecyclerView recyclerViewFoodList;
    private Button buttonAddFood;
    private FoodAdapter foodAdapter;
    private List<Food> foodList;
    private FoodManager foodManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_list_activity);

        recyclerViewFoodList = findViewById(R.id.recyclerViewFoodList);
        buttonAddFood = findViewById(R.id.buttonAddFood);

        // Nastavení layout manageru pro RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewFoodList.setLayoutManager(layoutManager);

        // Vytvoření instance FoodManager a načtení seznamu potravin
        foodManager = new FoodManager(this);
        loadFoodList();

        // Inicializace adaptéru pro RecyclerView
        foodAdapter = new FoodAdapter(this, foodList);
        recyclerViewFoodList.setAdapter(foodAdapter);

        // Nastavení posluchače pro tlačítko "Přidat potravinu"
        buttonAddFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Otevřít aktivitu pro přidání potraviny
                Intent intent = new Intent(FoodListActivity.this, AddFoodActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        foodList = foodManager.loadFoodList(this);
        foodAdapter.setFoodList(foodList);
    }



    private void loadFoodList() {
        foodList = foodManager.getFoodList();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            updateFoodList();
        }
    }


    private void updateFoodList() {
        foodList = foodManager.loadFoodList(this);
        foodAdapter.setFoodList(foodList);
    }


}