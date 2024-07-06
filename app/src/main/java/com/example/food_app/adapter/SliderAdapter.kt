package com.example.food_app.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.food_app.R
import com.example.food_app.domain.SliderItems

class SliderAdapter(
    sliderItems: ArrayList<SliderItems?>,
    private val viewPager2: ViewPager2
) :
    RecyclerView.Adapter<SliderAdapter.viewHolder>() {
    private val sliderItems: ArrayList<SliderItems?> = sliderItems
    private var context: Context? = null
    private val runnable = Runnable {
        sliderItems.addAll(sliderItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        context = parent.context
        val inflate =
            LayoutInflater.from(parent.context).inflate(R.layout.slider_viewholder, parent, false)
        return viewHolder(inflate)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        var requestOptions = RequestOptions()
        requestOptions = requestOptions.transform(CenterCrop(), RoundedCorners(60))
        Glide.with(context!!).load(sliderItems[position]!!.image).apply(requestOptions)
            .into(holder.imageView)

        if (position == sliderItems.size - 2) {
            viewPager2.post(runnable)
        }
    }

    override fun getItemCount(): Int {
        return sliderItems.size
    }

    inner class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal val imageView: ImageView = itemView.findViewById(R.id.imageSlide)
    }
}