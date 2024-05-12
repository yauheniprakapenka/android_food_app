package com.example.food_app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.food_app.adapter.CartAdapter;
import com.example.food_app.databinding.ActivityCartBinding;
import com.example.food_app.helper.ChangeNumberItemsListener;
import com.example.food_app.helper.ManagmentCart;

public class CartActivity extends AppCompatActivity {
    ActivityCartBinding binding;
    private ManagmentCart managmentCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        managmentCart = new ManagmentCart(this);
        setVariable();
        calculateCart();
        initCartList();
    }

    private void initCartList() {
        if (managmentCart.getListCart().isEmpty()) {
            binding.emptyTxt.setVisibility(View.VISIBLE);
            binding.scrollViewCart.setVisibility(View.GONE);
        } else {
            binding.emptyTxt.setVisibility(View.GONE);
            binding.scrollViewCart.setVisibility(View.VISIBLE);
        }
        binding.cartView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.cartView.setAdapter(new CartAdapter(managmentCart.getListCart(), managmentCart, new ChangeNumberItemsListener() {
            @Override
            public void change() {
                calculateCart();
            }
        }));
    }

    private void calculateCart() {
        double percentTax = 0.02;
        double delivery = 10;
        double tax = Math.round(managmentCart.getTotalFee() * percentTax * 100.0) / 100.0;
        double total = Math.round((managmentCart.getTotalFee() + tax + delivery) * 100.0) / 100.0;
        double itemTotal = Math.round(managmentCart.getTotalFee() * 100.0) / 100.0;

        binding.totalFeeTxt.setText("$" + itemTotal);
        binding.taxTxt.setText("$" + tax);
        binding.deliveryTxt.setText("$" + delivery);
        binding.totalTxt.setText("$" + total);
    }

    private void setVariable() {
        binding.backButton.setOnClickListener(v -> {
            startActivity(new Intent(CartActivity.this, MainActivity.class));
        });
    }
}