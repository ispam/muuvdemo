package com.example.muuvdemo.main_screen.data.local

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
                when (val service =  apiService.getUsers()) {
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
            }
        }
    }

    override suspend fun loadNextUsers() {

    }

    override suspend fun getUsersStateFlow(): Flow<PaginatedDataState<User>> = usersStateFlow
}