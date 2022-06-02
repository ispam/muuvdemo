package com.example.muuvdemo.splash_screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.muuvdemo.databinding.ActivitySplashscreenBinding
import com.example.muuvdemo.main_screen.MainActivity
import com.example.muuvdemo.utils.delayedRunner
import com.example.muuvdemo.utils.viewBinding

class SplashscreenActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivitySplashscreenBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        delayedRunner {
            Intent(this, MainActivity::class.java).apply {
                startActivity(this)
                finish()
            }
        }
    }
}