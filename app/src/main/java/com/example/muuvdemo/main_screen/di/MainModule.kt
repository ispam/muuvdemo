package com.example.muuvdemo.main_screen.di

import com.example.muuvdemo.di.NetworkModule
import com.example.muuvdemo.di.annotations.IoDispatcher
import com.example.muuvdemo.main_screen.data.local.UsersRepository
import com.example.muuvdemo.main_screen.data.local.UsersRepositoryImpl
import com.example.muuvdemo.main_screen.data.remote.APIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.CoroutineDispatcher

@Module(includes = [NetworkModule::class])
@InstallIn(value = [ViewModelComponent::class])
object MainModule {

    @Provides
    fun provideUsersRepositoryImpl(
        @IoDispatcher
        dispatcher: CoroutineDispatcher,
        apiService: APIService
    ): UsersRepository = UsersRepositoryImpl(dispatcher, apiService)
}