package com.example.muuvdemo.main_screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.muuvdemo.databinding.ActivityMainBinding
import com.example.muuvdemo.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }
}