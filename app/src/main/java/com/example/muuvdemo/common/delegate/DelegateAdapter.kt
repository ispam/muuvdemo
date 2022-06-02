package com.example.muuvdemo.common.delegate

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

interface DelegateAdapter<in VH : RecyclerView.ViewHolder, T : ItemDelegate> {

    fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder
    fun onBindViewHolder(viewHolder: VH, item: T)

    fun onViewRecycled(viewHolder: VH) = Unit
    fun onViewDetachedFromWindow(viewHolder: VH) = Unit
    fun onViewAttachedToWindow(viewHolder: VH) = Unit
}