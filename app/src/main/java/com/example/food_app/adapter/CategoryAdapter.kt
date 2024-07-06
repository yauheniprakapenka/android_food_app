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
import com.example.food_app.R
import com.example.food_app.activity.ListFoodActivity
import com.example.food_app.domain.Category

class CategoryAdapter(private var items: ArrayList<Category?>) :
    RecyclerView.Adapter<CategoryAdapter.viewHolder>() {
    var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        context = parent.context
        val inflate =
            LayoutInflater.from(parent.context).inflate(R.layout.viewholder_category, parent, false)
        return viewHolder(inflate)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val category = items[position]!!

        holder.titleTxt.text = category.name
        val path = category.imagePath
        Glide.with(context!!)
            .load(path)
            .into(holder.imageView)
        holder.itemView.setOnClickListener {
            val intent = Intent(
                context,
                ListFoodActivity::class.java
            )
            intent.putExtra("CategoryId", category.id)
            intent.putExtra("CategoryName", category.name)
            context!!.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var titleTxt: TextView = itemView.findViewById(R.id.catNameTxt)
        var imageView: ImageView = itemView.findViewById(R.id.imgCat)
    }
}