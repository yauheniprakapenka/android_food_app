package com.example.food_app.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.food_app.R;
import com.example.food_app.activity.DetailActivity;
import com.example.food_app.domain.Foods;

import java.util.ArrayList;

public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.viewHolder> {
    ArrayList<Foods> items;
    Context context;

    public FoodListAdapter(ArrayList<Foods> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public FoodListAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new viewHolder(LayoutInflater.from(context).inflate(R.layout.viewholder_list_food, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FoodListAdapter.viewHolder holder, int position) {
        Foods food = items.get(position);

        holder.titleTxt.setText(food.getTitle());
        holder.timeTxt.setText(String.valueOf(food.getTimeValue()) + " min");
        holder.priceTxt.setText("$" + String.valueOf(food.getPrice()));
        String imagePath = food.getImagePath();

        Glide.with(context)
                .load(imagePath)
                .transform(new CenterCrop(), new RoundedCorners(50))
                .into(holder.pic);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("object", food);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView titleTxt, priceTxt, rateTxt, timeTxt;
        ImageView pic;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            titleTxt = itemView.findViewById(R.id.titleTxt);
            priceTxt = itemView.findViewById(R.id.priceTxt);
            rateTxt = itemView.findViewById(R.id.ratingTxt);
            timeTxt = itemView.findViewById(R.id.timeTxt);
            pic = itemView.findViewById(R.id.list_food_img);
        }
    }
}
