package com.example.muuvdemo.main_screen.fragments

import com.example.muuvdemo.common.BaseFragment
import com.example.muuvdemo.databinding.FragmentUserBinding
import com.example.muuvdemo.main_screen.data.local.entities.User
import com.squareup.picasso.Picasso

class UserFragment : BaseFragment<FragmentUserBinding>() {

    override fun onViewCreated() {

        arguments?.getParcelable<User>(SINGLE_USER_KEY)?.let {
            Picasso.get().load(it.avatar).into(binding.userAvatar)
            binding.userNameText.text = "${it.first_name} ${it.last_name}"
            binding.userEmailText.text = it.email
        }
    }

    companion object {
        private const val SINGLE_USER_KEY = "singleUser"
    }
}