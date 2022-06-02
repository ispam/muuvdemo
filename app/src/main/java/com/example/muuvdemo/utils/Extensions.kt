package com.example.muuvdemo.utils

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.muuvdemo.common.delegate.DelegateAdapter
import com.example.muuvdemo.common.delegate.ItemDelegate

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

fun DelegateAdapter<*, *>.toDA(): DelegateAdapter<RecyclerView.ViewHolder, ItemDelegate>? {
    return this as? DelegateAdapter<RecyclerView.ViewHolder, ItemDelegate>
}