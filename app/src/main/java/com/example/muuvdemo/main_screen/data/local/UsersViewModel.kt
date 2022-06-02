package com.example.muuvdemo.main_screen.data.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.muuvdemo.main_screen.data.local.entities.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel
@Inject constructor(
    private val usersRepository: UsersRepository
): ViewModel() {

    private val _userListLiveData = MutableLiveData<List<User>>()
    val userListLivaData: LiveData<List<User>> = _userListLiveData

    init {
        viewModelScope.launch {
            usersRepository.load()
            usersRepository
                .getUsersStateFlow()
                .collect { paginatedDataState ->
                    _userListLiveData.value = paginatedDataState.items
            }
        }
    }
}
