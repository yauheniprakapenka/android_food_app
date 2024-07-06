package com.example.food_app.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.food_app.adapter.CartAdapter
import com.example.food_app.databinding.ActivityCartBinding
import com.example.food_app.helper.ManagmentCart

class CartActivity : AppCompatActivity() {
    var binding: ActivityCartBinding? = null
    private var managmentCart: ManagmentCart? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        managmentCart = ManagmentCart(this)
        setVariable()
        calculateCart()
        initCartList()
    }

    private fun initCartList() {
        if (managmentCart!!.listCart.isEmpty()) {
            binding!!.emptyTxt.visibility = View.VISIBLE
            binding!!.scrollViewCart.visibility = View.GONE
        } else {
            binding!!.emptyTxt.visibility = View.GONE
            binding!!.scrollViewCart.visibility = View.VISIBLE
        }
        binding!!.cartView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding!!.cartView.adapter = CartAdapter(
            managmentCart!!.listCart, managmentCart!!
        ) { calculateCart() }
    }

    private fun calculateCart() {
        val percentTax = 0.02
        val delivery = 10.0
        val tax = Math.round(managmentCart!!.totalFee * percentTax * 100.0) / 100.0
        val total = Math.round((managmentCart!!.totalFee + tax + delivery) * 100.0) / 100.0
        val itemTotal = Math.round(managmentCart!!.totalFee * 100.0) / 100.0

        binding!!.totalFeeTxt.text = "$$itemTotal"
        binding!!.taxTxt.text = "$$tax"
        binding!!.deliveryTxt.text = "$$delivery"
        binding!!.totalTxt.text = "$$total"
    }

    private fun setVariable() {
        binding!!.backButton.setOnClickListener { v ->
            startActivity(
                Intent(
                    this@CartActivity,
                    MainActivity::class.java
                )
            )
        }
    }
}