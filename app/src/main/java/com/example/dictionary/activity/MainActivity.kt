package com.example.dictionary.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.dictionary.R
import com.example.dictionary.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        val imvSplash = binding.imvSplashScreen
        val fragment = binding.navHostFragment
        imvSplash.apply {
            alpha = 0f
            animate().setDuration(3000).alpha(1f)
                .withEndAction{
                    fragment.visibility = View.VISIBLE
                    imvSplash.visibility = View.GONE
                    overridePendingTransition(android.R.anim.fade_in ,
                    android.R.anim.fade_out)
                }
        }

    }
}