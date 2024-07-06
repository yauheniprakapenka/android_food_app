package com.example.food_app.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.food_app.R
import com.google.firebase.database.FirebaseDatabase

open class BaseActivity : AppCompatActivity() {
    var database: FirebaseDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        database = FirebaseDatabase.getInstance()
        setContentView(R.layout.activity_base)
    }
}