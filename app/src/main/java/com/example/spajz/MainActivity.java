/**
 * Author: Zdeněk Carbol, r22430
 */
package com.example.spajz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Main class of the application
 */
public class MainActivity extends AppCompatActivity {
    /**
     * Method for creating activity_main
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setFoodListButton();
        setShoppingListButton();
    }

    /**
     * Method for setting foodListButton
     */
    private void setFoodListButton() {
        Button foodListButton = findViewById(R.id.foodListButton);

        foodListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FoodListActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Method for setting shoppingListButton
     */
    private void setShoppingListButton() {
        Button buttonShoppingList = findViewById(R.id.shoppingListButton);

        buttonShoppingList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShoppingListActivity.class);
                startActivity(intent);
            }
        });
    }
}
