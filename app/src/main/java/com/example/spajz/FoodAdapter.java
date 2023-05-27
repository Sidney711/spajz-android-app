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

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    private Context context;
    private List<Food> foodList;
    private OnDeleteClickListener onDeleteClickListener;

    public FoodAdapter(Context context, List<Food> foodList) {
        this.context = context;
        this.foodList = foodList;
    }

    public interface OnDeleteClickListener {
        void onDeleteClick(int position);
    }

    public void setOnDeleteClickListener(OnDeleteClickListener listener) {
        onDeleteClickListener = listener;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_food, parent, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        Food food = foodList.get(position);
        holder.textViewFoodName.setText(food.getName());
        holder.textViewFoodCount.setText(String.valueOf(food.getCount()));
    }


    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
        notifyDataSetChanged();
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder {
        TextView textViewFoodName;
        TextView textViewFoodCount;
        ImageView imageViewDeleteFood;
        ImageView imageViewEditFood;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewFoodName = itemView.findViewById(R.id.textViewFoodName);
            textViewFoodCount = itemView.findViewById(R.id.textViewFoodCount);
            imageViewDeleteFood = itemView.findViewById(R.id.imageViewDeleteFood);
            imageViewEditFood = itemView.findViewById(R.id.imageViewEditFood);

            imageViewDeleteFood.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && onDeleteClickListener != null) {
                        onDeleteClickListener.onDeleteClick(position);
                    }
                }
            });

            imageViewEditFood.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        editFood(position);
                    }
                }
            });
        }
    }


    public void editFood(int position) {
        // Získání potraviny na zadané pozici
        Food food = foodList.get(position);

        // Vytvoření intentu pro spuštění UpdateFoodActivity
        Intent intent = new Intent(context, UpdateFoodActivity.class);
        intent.putExtra("foodName", food.getName());
        intent.putExtra("foodCount", food.getCount());
        intent.putExtra("position", position);
        ((Activity) context).startActivityForResult(intent, 1);
    }

}
