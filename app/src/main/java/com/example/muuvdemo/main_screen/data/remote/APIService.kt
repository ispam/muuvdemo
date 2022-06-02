package com.example.muuvdemo.main_screen.data.remote

import com.example.muuvdemo.main_screen.data.local.entities.DataPage
import com.example.muuvdemo.main_screen.data.local.entities.User
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("api/users")
    suspend fun getUsers(
        @Query("page")
        page: Int
    ): DataPage<User>?
}