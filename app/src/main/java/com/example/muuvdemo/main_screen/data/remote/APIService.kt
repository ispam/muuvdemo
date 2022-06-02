package com.example.muuvdemo.main_screen.data.remote

import com.example.muuvdemo.main_screen.data.local.entities.DataPage
import com.example.muuvdemo.main_screen.data.local.entities.User
import retrofit2.http.GET

interface APIService {

    @GET("api/users?page=1")
    suspend fun getUsers(): DataPage<User>?
}