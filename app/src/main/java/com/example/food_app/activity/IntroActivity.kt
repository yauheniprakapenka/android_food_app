package com.example.food_app.activity

import android.content.Intent
import android.os.Bundle
import com.example.food_app.databinding.ActivityIntroBinding

class IntroActivity : BaseActivity() {
    var binding: ActivityIntroBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        binding!!.goBtn.setOnClickListener {
            startActivity(
                Intent(
                    this@IntroActivity,
                    MainActivity::class.java
                )
            )
        }
    }
}