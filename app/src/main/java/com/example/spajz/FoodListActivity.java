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

public class FoodListActivity extends AppCompatActivity implements FoodAdapter.OnDeleteClickListener {
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

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewFoodList.setLayoutManager(layoutManager);

        foodManager = new FoodManager(this);
        loadFoodList();

        foodAdapter = new FoodAdapter(this, foodList);
        foodAdapter.setOnDeleteClickListener(this);
        recyclerViewFoodList.setAdapter(foodAdapter);

        buttonAddFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FoodListActivity.this, AddFoodActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadFoodList();
        foodAdapter.setFoodList(foodList);
    }

    private void loadFoodList() {
        foodList = foodManager.getFoodList();
    }

    private void deleteFood(int position) {
        Food food = foodList.get(position);
        foodManager.deleteFood(food);
        foodList.remove(position);
        foodAdapter.notifyItemRemoved(position);
        Toast.makeText(this, "Potravina " + food.getName() + " byla smazána.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteClick(int position) {
        deleteFood(position);
    }
}
