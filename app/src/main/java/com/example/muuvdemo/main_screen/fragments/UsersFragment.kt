package com.example.muuvdemo.main_screen.fragments

import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
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
class UsersFragment : BaseFragment<FragmentUsersBinding>() {

    private val viewModel by navGraphViewModels<UsersViewModel>(R.id.main_graph) {
        defaultViewModelProviderFactory
    }

    private lateinit var usersAdapter: UsersAdapter
    private lateinit var llm : LinearLayoutManager

    override fun onViewCreated() {
        setupAdapter()
        showDialog(requireContext())

        observe(viewModel.userListLivaData, ::onUserData)
    }

    private fun onUserData(list: List<User>) {
        delayedBlock {
            closeDialog()
            usersAdapter.submitList(list)
        }
    }

    private fun RecyclerView.setScrollListener() {
        addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (llm.findLastVisibleItemPosition() == usersAdapter.itemCount - 1) {
                    viewModel.loadMoreUsers()
                }
            }
        })
    }

    private fun setupAdapter() {
        usersAdapter = UsersAdapter() {
            val bundle = bundleOf(SINGLE_USER_KEY to it)
            findNavController()
                .navigate(
                   R.id.action_usersFragment_to_userFragment, bundle
                )
        }
        binding.usersRecycler.apply {
            llm = LinearLayoutManager(
                requireContext(),
                RecyclerView.VERTICAL,
                false
            )
            layoutManager = llm
            adapter = usersAdapter
            setScrollListener()
        }
    }

    companion object {
        private const val SINGLE_USER_KEY = "singleUser"
    }
}