package com.example.muuvdemo.main_screen.adapters

import com.example.muuvdemo.common.delegate.GenericViewAdapter
import com.example.muuvdemo.common.delegate.ItemDelegate
import com.example.muuvdemo.utils.USER_TYPE
import com.example.muuvdemo.utils.toDA

class UsersAdapter: GenericViewAdapter<ItemDelegate>() {

    init {
        delegateAdapters.apply {
            put(USER_TYPE, UserDelegate().toDA())
        }
    }
}