package com.example.muuvdemo.main_screen.data.local

import com.example.muuvdemo.main_screen.data.local.entities.PaginatedDataState
import com.example.muuvdemo.main_screen.data.local.entities.User
import kotlinx.coroutines.flow.Flow

interface UsersRepository {

    suspend fun load()

    suspend fun loadNextUsers()

    suspend fun getUsersStateFlow(): Flow<PaginatedDataState<User>>
}