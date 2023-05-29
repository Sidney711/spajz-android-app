/**
 * Author: Zdeněk Carbol, r22430
 */
package com.example.spajz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * FoodAdapter class, child of RecyclerView.Adapter, between data & user interface
 */
public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {
    /**
     * Attributes of the class
     */
    private final Context context;
    private List<Food> foodList;
    private OnDeleteClickListener onDeleteClickListener;
    private boolean hideButtons = false;

    /**
     * Easy constructor for the FoodAdapter
     * @param context Context for getting layouts, information, ...
     * @param foodList List<Food> list of the foods
     */
    public FoodAdapter(Context context, List<Food> foodList) {
        this.context = context;
        this.foodList = foodList;
    }

    /**
     * This method is for setting hideButtons
     * @param hideButtons boolean it says if delete & update buttons should be hidden the buttons
     *                    are hidden e.g. in shopping list
     */
    public void setHideButtons(boolean hideButtons) {
        this.hideButtons = hideButtons;
    }

    /**
     * Set listener for click on delete button
     * @param listener OnDeleteClickListener
     */
    public void setOnDeleteClickListener(OnDeleteClickListener listener) {
        onDeleteClickListener = listener;
    }

    /**
     * onCreateViewHolder method
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return FoodViewHolder
     */
    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_food, parent, false);

        return new FoodViewHolder(view);
    }

    /**
     * onBindViewHolder method
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        // Creating reference on new Food object
        Food food = foodList.get(position);

        // Setting data of the food
        holder.textViewFoodName.setText(food.getName());
        holder.textViewFoodCount.setText(String.valueOf(food.getCount()));

        // If the user watch foods in shopping list buttons will be hidden
        if (hideButtons) {
            holder.imageViewDeleteFood.setVisibility(View.GONE);
            holder.imageViewEditFood.setVisibility(View.GONE);
        }
    }

    /**
     * This method returns count of the foods
     * @return int
     */
    @Override
    public int getItemCount() {
        return foodList.size();
    }

    /**
     * This method sets food list
     * @param foodList List<Food> food list which will be set
     */
    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
        notifyDataSetChanged();
    }

    /**
     * Method for editing food
     * @param position int position of the food which will be edited
     */
    public void editFood(int position) {
        // Getting food from the position
        Food food = foodList.get(position);

        // Intent for running UpdateFoodActivity
        Intent intent = new Intent(context, UpdateFoodActivity.class);

        // Putting data in the inputs
        intent.putExtra("foodName", food.getName());
        intent.putExtra("foodCount", food.getCount());
        intent.putExtra("position", position);

        // Run update the food activity
        ((Activity) context).startActivityForResult(intent, 1);
    }

    /**
     * Interface for the listener for delete a food
     */
    public interface OnDeleteClickListener {
        void onDeleteClick(int position);
    }

    /**
     * Class FoodViewHolder for showing the foods
     */
    public class FoodViewHolder extends RecyclerView.ViewHolder {
        /**
         * Attributes of the class
         */
        TextView textViewFoodName;
        TextView textViewFoodCount;
        ImageView imageViewDeleteFood;
        ImageView imageViewEditFood;

        /**
         * Constructor of the FoodViewHolder, some listeners are there too
         * @param itemView View one food item
         */
        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewFoodName = itemView.findViewById(R.id.textViewFoodName);
            textViewFoodCount = itemView.findViewById(R.id.textViewFoodCount);
            imageViewDeleteFood = itemView.findViewById(R.id.imageViewDeleteFood);
            imageViewEditFood = itemView.findViewById(R.id.imageViewEditFood);

            // Listener for the delete button
            imageViewDeleteFood.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    // If the position is not valid and onDeleteClickListener is set
                    if (position != RecyclerView.NO_POSITION && onDeleteClickListener != null) {
                        // Food is deleted from the list
                        onDeleteClickListener.onDeleteClick(position);
                    }
                }
            });

            // Listener for the update button
            imageViewEditFood.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    // If the position is not valid
                    if (position != RecyclerView.NO_POSITION) {
                        // Edit food action
                        editFood(position);
                    }
                }
            });
        }
    }
}
