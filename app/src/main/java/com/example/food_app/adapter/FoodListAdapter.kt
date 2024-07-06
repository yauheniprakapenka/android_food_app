package com.example.food_app.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.food_app.R
import com.example.food_app.activity.DetailActivity
import com.example.food_app.domain.Foods

class FoodListAdapter(var items: ArrayList<Foods?>) :
    RecyclerView.Adapter<FoodListAdapter.viewHolder>() {
    var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        context = parent.context
        return viewHolder(
            LayoutInflater.from(context).inflate(R.layout.viewholder_list_food, parent, false)
        )
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val food = items[position]!!

        holder.titleTxt.text = food.title
        holder.timeTxt.text = food.timeValue.toString() + " min"
        holder.priceTxt.text = "$" + food.price.toString()
        val imagePath = food.imagePath

        Glide.with(context!!)
            .load(imagePath)
            .transform(CenterCrop(), RoundedCorners(50))
            .into(holder.pic)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("object", food)
            context!!.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var titleTxt: TextView = itemView.findViewById(R.id.titleTxt)
        var priceTxt: TextView = itemView.findViewById(R.id.priceTxt)
        var rateTxt: TextView = itemView.findViewById(R.id.ratingTxt)
        var timeTxt: TextView = itemView.findViewById(R.id.timeTxt)
        var pic: ImageView = itemView.findViewById(R.id.list_food_img)
    }
}