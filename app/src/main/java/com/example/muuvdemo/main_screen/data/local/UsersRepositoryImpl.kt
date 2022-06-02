package com.example.muuvdemo.main_screen.data.local

import android.util.Log
import com.example.muuvdemo.main_screen.data.local.entities.PaginatedDataState
import com.example.muuvdemo.main_screen.data.local.entities.User
import com.example.muuvdemo.main_screen.data.remote.APIService
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class UsersRepositoryImpl
@Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val apiService: APIService
) : UsersRepository {

    private val usersStateFlow = MutableStateFlow(PaginatedDataState<User>(pageSize = 6))

    override suspend fun load() {
        withContext(dispatcher) {
            kotlin.runCatching {
                when (val service =  apiService.getUsers(INITIAL_PAGE)) {
                    null -> throw IllegalStateException("No data was received by calling getUsers")
                    else -> service
                }
            }.onSuccess { dataPage ->
                usersStateFlow.apply {
                    emit(value.onPageLoaded(dataPage))
                }
            }.onFailure {
                usersStateFlow.apply {
                    emit(value.onPageError(it))
                }
                Log.e("load", it.message!!)
            }
        }
    }

    override suspend fun loadNextUsers() {
        withContext(dispatcher) {
            usersStateFlow.value.apply {
                if (hasNextPage && !loading) {
                    kotlin.runCatching {
                        usersStateFlow.emit(onLoading())
                        when (val service =  apiService.getUsers(nextPageToLoad)) {
                            null -> throw IllegalStateException("No data was received by calling loadNextUsers")
                            else -> service
                        }
                    }.onSuccess { dataPage ->
                        usersStateFlow.emit(onPageLoaded(dataPage))
                    }.onFailure {
                        usersStateFlow.emit(onPageError(it))
                    }
                }
            }
        }
    }

    override suspend fun getUsersStateFlow(): Flow<PaginatedDataState<User>> = usersStateFlow

    companion object {
        private const val INITIAL_PAGE = 1
    }
}