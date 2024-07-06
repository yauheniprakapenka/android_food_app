package com.example.food_app.activity

import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.food_app.databinding.ActivityDetailBinding
import com.example.food_app.domain.Foods
import com.example.food_app.helper.ManagmentCart

open class DetailActivity : BaseActivity() {
    private var managmentCart: ManagmentCart? = null
    var binding: ActivityDetailBinding? = null
    private var `object`: Foods? = null
    private var num = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        intentExtra
        setVariable()
    }

    private fun setVariable() {
        managmentCart = ManagmentCart(this)
        binding!!.backButton.setOnClickListener { v -> finish() }
        Glide.with(this).load(`object`!!.imagePath).transform(CenterCrop(), RoundedCorners(60))
            .into(
                binding!!.pic
            )
        binding!!.priceTxt.text = "$" + `object`!!.price
        binding!!.titleTxt.text = `object`!!.title
        binding!!.descriptionTxt.text = `object`!!.description
        binding!!.totalTxt.text = `object`!!.star.toString() + " Stars"
        binding!!.ratingBar.rating = `object`!!.star
        binding!!.totalTxt.text = (num * `object`!!.price).toString() + "$"
        binding!!.timeTxt.text = `object`!!.timeValue.toString() + " min"

        binding!!.plusButton.setOnClickListener {
            num = num + 1
            binding!!.numTxt.text = num.toString()
            binding!!.totalTxt.text = "$" + num * `object`!!.price
        }

        binding!!.minusButton.setOnClickListener {
            if (num > 1) {
                num = num - 1
                binding!!.numTxt.text = num.toString()
                binding!!.totalTxt.text = "$" + num * `object`!!.price
            }
        }

        binding!!.addButton.setOnClickListener {
            `object`!!.numberInCart = num
            managmentCart!!.insertFood(`object`)
        }
    }

    private val intentExtra: Unit
        get() {
            `object` = intent.getSerializableExtra("object") as Foods?
        }
}