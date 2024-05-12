package com.example.food_app.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.food_app.R;
import com.example.food_app.domain.Foods;
import com.example.food_app.helper.ChangeNumberItemsListener;
import com.example.food_app.helper.ManagmentCart;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.viewHolder> {
    ArrayList<Foods> list;
    ChangeNumberItemsListener changeNumberItemsListener;
    private ManagmentCart managmentCart;

    public CartAdapter(ArrayList<Foods> list, ManagmentCart managmentCart, ChangeNumberItemsListener changeNumberItemsListener) {
        this.list = list;
        this.managmentCart = managmentCart;
        this.changeNumberItemsListener = changeNumberItemsListener;
    }

    @NonNull
    @Override
    public CartAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.viewHolder holder, int position) {
        Foods food = list.get(position);

        holder.title.setText(food.getTitle());
        holder.feeEachItem.setText("$" + String.valueOf(food.getNumberInCart() * food.getPrice()));
        holder.num.setText(String.valueOf(food.getNumberInCart()));

        Glide.with(holder.pic.getContext())
                .load(food.getImagePath())
                .transform(new CenterCrop(), new RoundedCorners(50))
                .into(holder.pic);

        holder.plusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managmentCart.plusNumberItem(list, position, new ChangeNumberItemsListener() {
                    @Override
                    public void change() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.change();
                    }
                });
            }
        });

        holder.minusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managmentCart.minusNumberItem(list, position, new ChangeNumberItemsListener() {
                    @Override
                    public void change() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.change();
                    }
                });
            }
        });

        holder.trashButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                managmentCart.removeItem(list, position, () -> {
                    notifyDataSetChanged();
                    changeNumberItemsListener.change();
                });
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView title, feeEachItem, plusItem, minusItem;
        ImageView pic;
        TextView num;
        ConstraintLayout trashButton;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.titleTxt);
            pic = itemView.findViewById(R.id.pic);
            feeEachItem = itemView.findViewById(R.id.feeEachItem);
            plusItem = itemView.findViewById(R.id.plusCartButton);
            minusItem = itemView.findViewById(R.id.minusCartButton);
            num = itemView.findViewById(R.id.numberItemTxt);
            trashButton = itemView.findViewById(R.id.trashButton);
        }
    }
}
