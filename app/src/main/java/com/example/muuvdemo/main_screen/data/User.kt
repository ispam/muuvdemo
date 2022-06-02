package com.example.muuvdemo.main_screen.data

import com.example.muuvdemo.common.delegate.ItemDelegate
import com.example.muuvdemo.utils.USER_TYPE

data class User(
    val id: Int,
    val email: String,
    val first_name: String,
    val last_name: String,
    val avatar: String
): ItemDelegate {
    override fun getViewType(): Int = USER_TYPE
    override fun id(): String = id.toString()
    override fun content(): Any = this
}
