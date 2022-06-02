package com.example.muuvdemo.main_screen.fragments

import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.muuvdemo.R
import com.example.muuvdemo.common.BaseFragment
import com.example.muuvdemo.databinding.FragmentUsersBinding
import com.example.muuvdemo.main_screen.adapters.UsersAdapter
import com.example.muuvdemo.main_screen.data.local.UsersViewModel
import com.example.muuvdemo.main_screen.data.local.entities.User
import com.example.muuvdemo.utils.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsersFragment: BaseFragment<FragmentUsersBinding>() {

    private val viewModel by navGraphViewModels<UsersViewModel>(R.id.main_graph) {
        defaultViewModelProviderFactory
    }

    private lateinit var usersAdapter: UsersAdapter

    override fun onViewCreated() {
        setupAdapter()

        observe(viewModel.userListLivaData, ::onUserData)
    }

    private fun onUserData(list: List<User>) {
        usersAdapter.submitList(list)
    }

    private fun setupAdapter() {
        usersAdapter = UsersAdapter()
        binding.usersRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = usersAdapter
        }
    }
}