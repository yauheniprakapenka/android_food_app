package com.example.food_app.activity;

import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.food_app.databinding.ActivityDetailBinding;
import com.example.food_app.domain.Foods;
import com.example.food_app.helper.ManagmentCart;

public class DetailActivity extends BaseActivity {
    protected ManagmentCart managmentCart;
    ActivityDetailBinding binding;
    private Foods object;
    private int num = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getIntentExtra();
        setVariable();
    }

    private void setVariable() {
        managmentCart = new ManagmentCart(this);
        binding.backButton.setOnClickListener(v -> finish());
        Glide.with(this).load(object.getImagePath()).transform(new CenterCrop(), new RoundedCorners(60)).into(binding.pic);
        binding.priceTxt.setText("$" + object.getPrice());
        binding.titleTxt.setText(object.getTitle());
        binding.descriptionTxt.setText(object.getDescription());
        binding.totalTxt.setText(object.getStar() + " Stars");
        binding.ratingBar.setRating(object.getStar());
        binding.totalTxt.setText((num * object.getPrice()) + "$");

        binding.plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num = num + 1;
                binding.numTxt.setText(num);
                binding.totalTxt.setText("$" + num * object.getPrice());
            }
        });

        binding.minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (num > 1) {
                    num = num - 1;
                    binding.numTxt.setText(num);
                    binding.totalTxt.setText("$" + num * object.getPrice());
                }
            }
        });

        binding.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                object.setNumberInCart(num);
                managmentCart.insertFood(object);
            }
        });
    }

    private void getIntentExtra() {
        object = (Foods) getIntent().getSerializableExtra("object");
    }

}
