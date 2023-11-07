package com.example.tpseminario.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tpseminario.model.User
import com.example.tpseminario.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    private val userRepository = UserRepository()

    val readAllData: LiveData<List<User>> = userRepository.readAllData


    fun insertUser(user: User) {
        viewModelScope.launch {
            userRepository.insertUser(user = user)
        }
    }


    fun updateUser(user: User) {
        //CoroutineScope(Dispatchers.IO).launch {}
        viewModelScope.launch {
            userRepository.updateUser(user = user)
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch {
            userRepository.deleteUser(user = user)
        }
    }

    fun deleteAllUsers() {
        viewModelScope.launch {
            userRepository.deleteAllUsers()
        }
    }

}