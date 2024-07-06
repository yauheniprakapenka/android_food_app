package com.example.food_app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.food_app.R
import com.example.food_app.domain.Foods
import com.example.food_app.helper.ChangeNumberItemsListener
import com.example.food_app.helper.ManagmentCart

class CartAdapter(
    var list: ArrayList<Foods>,
    private val managmentCart: ManagmentCart,
    var changeNumberItemsListener: ChangeNumberItemsListener
) :
    RecyclerView.Adapter<CartAdapter.viewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        return viewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.viewholder_cart, parent, false)
        )
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val food = list[position]

        holder.title.text = food.title
        holder.feeEachItem.text = "$" + (food.numberInCart * food.price).toString()
        holder.num.text = food.numberInCart.toString()

        Glide.with(holder.pic.context)
            .load(food.imagePath)
            .transform(CenterCrop(), RoundedCorners(50))
            .into(holder.pic)

        holder.plusItem.setOnClickListener {
            managmentCart.plusNumberItem(list, position) {
                notifyDataSetChanged()
                changeNumberItemsListener.change()
            }
        }

        holder.minusItem.setOnClickListener {
            managmentCart.minusNumberItem(list, position) {
                notifyDataSetChanged()
                changeNumberItemsListener.change()
            }
        }

        holder.trashButton.setOnClickListener {
            managmentCart.removeItem(list, position) {
                notifyDataSetChanged()
                changeNumberItemsListener.change()
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.titleTxt)
        var feeEachItem: TextView = itemView.findViewById(R.id.feeEachItem)
        var plusItem: TextView = itemView.findViewById(R.id.plusCartButton)
        var minusItem: TextView = itemView.findViewById(R.id.minusCartButton)
        var pic: ImageView = itemView.findViewById(R.id.pic)
        var num: TextView = itemView.findViewById(R.id.numberItemTxt)
        var trashButton: ConstraintLayout = itemView.findViewById(R.id.trashButton)
    }
}