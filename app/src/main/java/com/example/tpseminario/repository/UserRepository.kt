package com.example.tpseminario.repository

import androidx.lifecycle.LiveData
import com.example.tpseminario.data.UserDB
import com.example.tpseminario.model.User


class UserRepository {



    private val userDao = UserDB.getDatabase().userDao()

    val readAllData: LiveData<List<User>> = userDao.readAllData()


    suspend fun insertUser(user: User) {
        userDao.insertUser(user = user)
    }


    suspend fun updateUser(user: User) {
        userDao.updateUser(user = user)
    }

    suspend fun deleteUser(user: User) {
        userDao.deleteUser(user = user)
    }

    suspend fun deleteAllUsers() {
        userDao.deleteAll()
    }

}