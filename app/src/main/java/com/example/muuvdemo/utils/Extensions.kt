package com.example.muuvdemo.utils

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
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

inline fun <T: Any, L: LiveData<T>> LifecycleOwner.observe(liveData: L, crossinline body: (T) -> Unit) =
    liveData.observe(if (this is Fragment) viewLifecycleOwner else this, Observer { body(it) })