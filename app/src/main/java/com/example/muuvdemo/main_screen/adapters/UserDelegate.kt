package com.example.muuvdemo.main_screen.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.muuvdemo.common.delegate.DelegateAdapter
import com.example.muuvdemo.databinding.DelegateUserBinding
import com.example.muuvdemo.main_screen.data.local.entities.User

class UserDelegate : DelegateAdapter<UserDelegate.ViewHolder, User> {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return ViewHolder(
            DelegateUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, item: User) {
        viewHolder.bind(item)
    }

    inner class ViewHolder(private val binding: DelegateUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(user: User) {
                binding.userName.text = "${user.first_name} ${user.last_name}"
                binding.userEmail.text = user.email
                binding.userId.text = user.id.toString()
            }
    }
}