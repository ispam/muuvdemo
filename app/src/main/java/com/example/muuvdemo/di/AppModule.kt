package com.example.muuvdemo.di

import com.example.muuvdemo.Muuvdemo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(value = [SingletonComponent::class])
object AppModule {

    @Provides
    fun provideApplication(app: Muuvdemo): Muuvdemo = app
}