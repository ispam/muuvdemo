package com.example.muuvdemo.utils

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

inline fun <T : ViewBinding> AppCompatActivity.viewBinding(
    crossinline bindingInflater: (LayoutInflater) -> T
) = lazy(LazyThreadSafetyMode.NONE) {
    bindingInflater.invoke(layoutInflater)
}

fun delayedRunner(function: () -> Unit) {
    Handler(Looper.getMainLooper()).postDelayed({
        function.invoke()
    }, 2000L)
}