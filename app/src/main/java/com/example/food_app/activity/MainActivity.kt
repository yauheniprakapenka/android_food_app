package com.example.food_app.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.food_app.R
import com.example.food_app.adapter.CategoryAdapter
import com.example.food_app.adapter.SliderAdapter
import com.example.food_app.databinding.ActivityMainBinding
import com.example.food_app.domain.Category
import com.example.food_app.domain.SliderItems
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.ismaeldivita.chipnavigation.ChipNavigationBar

class MainActivity : BaseActivity() {
    var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        initCategory()
        initBanners()
        setVariable()
    }

    private fun setVariable() {
        binding!!.bottomNavBar.setItemSelected(R.id.nav_home, true)
        binding!!.bottomNavBar.setOnItemSelectedListener(object :
            ChipNavigationBar.OnItemSelectedListener {
            override fun onItemSelected(id: Int) {
                if (id == R.id.nav_cart) {
                    startActivity(
                        Intent(
                            this@MainActivity,
                            CartActivity::class.java
                        )
                    )
                }
            }
        })
    }

    private fun initBanners() {
        val ref = database!!.getReference("Banners")
        binding!!.progressBarBanner.visibility = View.VISIBLE
        val items = ArrayList<SliderItems?>()
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (issue in snapshot.children) {
                        items.add(issue.getValue(SliderItems::class.java))
                    }
                    banners(items)
                    binding!!.progressBarBanner.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    private fun banners(sliderItems: ArrayList<SliderItems?>) {
        binding!!.viewpager2.adapter = SliderAdapter(sliderItems, binding!!.viewpager2)
        binding!!.viewpager2.clipChildren = false
        binding!!.viewpager2.clipToPadding = false
        binding!!.viewpager2.offscreenPageLimit = 3

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(40))

        binding!!.viewpager2.setPageTransformer(compositePageTransformer)
    }

    private fun initCategory() {
        val ref = database!!.getReference("Category")
        binding!!.progressBarCategory.visibility = View.VISIBLE
        val list = ArrayList<Category?>()

        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (issue in snapshot.children) {
                        list.add(issue.getValue(Category::class.java))
                    }
                    if (!list.isEmpty()) {
                        binding!!.categoryView.layoutManager =
                            GridLayoutManager(this@MainActivity, 3)
                        binding!!.categoryView.adapter = CategoryAdapter(list)
                    }
                    binding!!.progressBarCategory.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }
}