package com.example.food_app.activity

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.food_app.adapter.FoodListAdapter
import com.example.food_app.databinding.ActivityListBinding
import com.example.food_app.domain.Foods
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class ListFoodActivity : BaseActivity() {
    var binding: ActivityListBinding? = null
    private var categoryId = 0
    private var categoryName: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)

        setContentView(binding!!.root)

        intentExtra
        initList()
    }

    private fun initList() {
        val ref = database!!.getReference("Foods")
        binding!!.progressBar.visibility = View.VISIBLE
        val list = ArrayList<Foods?>()
        val query = ref.orderByChild("CategoryId").equalTo(categoryId.toDouble())

        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (issue in snapshot.children) {
                        list.add(issue.getValue(Foods::class.java))
                    }
                    if (!list.isEmpty()) {
                        binding!!.foodListView.layoutManager = LinearLayoutManager(
                            this@ListFoodActivity,
                            LinearLayoutManager.VERTICAL,
                            false
                        )
                        binding!!.foodListView.adapter = FoodListAdapter(list)
                    }
                    binding!!.progressBar.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    private val intentExtra: Unit
        get() {
            categoryId = intent.getIntExtra("CategoryId", 0)
            categoryName = intent.getStringExtra("CategoryName")

            binding!!.titleTxt.text = categoryName
            binding!!.backButton.setOnClickListener { v -> finish() }
        }
}