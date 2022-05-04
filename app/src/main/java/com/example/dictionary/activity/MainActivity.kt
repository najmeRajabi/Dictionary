package com.example.dictionary.activity

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.dictionary.databinding.ActivityMainBinding
import com.example.dictionary.repository.WordViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val vModel: WordViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        supportActionBar?.hide()
        val imvSplash = binding.imvSplashScreen
        val fragment = binding.navHostFragment
        if (vModel.splashFlag) {
            imvSplash.apply {
                alpha = 0f
                animate().setDuration(3000).alpha(1f)
                    .withEndAction {
                        fragment.visibility = View.VISIBLE
                        imvSplash.visibility = View.GONE
                        overridePendingTransition(
                            android.R.anim.fade_in,
                            android.R.anim.fade_out
                        )
                    }
            }
            vModel.splashFlag = false
        }else{
            fragment.visibility = View.VISIBLE
            imvSplash.visibility = View.GONE
        }

    }
}