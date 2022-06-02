package com.example.muuvdemo.main_screen.data.local.entities

import com.example.muuvdemo.common.delegate.ParcelableItemDelegate
import com.example.muuvdemo.utils.USER_TYPE
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: Int,
    val email: String,
    val first_name: String,
    val last_name: String,
    val avatar: String
): ParcelableItemDelegate {
    override fun getViewType(): Int = USER_TYPE
    override fun id(): String = id.toString()
    override fun content(): Any = this
}
