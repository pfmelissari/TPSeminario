package com.example.tpseminario.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tpseminario.TpSeminarioApp
import com.example.tpseminario.model.User
import com.example.tpseminario.utils.Constants


@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDB : RoomDatabase() {

    abstract fun userDao(): UserDao



    companion object{
        @Volatile
        private var INSTANCE: UserDB? = null


        fun getDatabase(): UserDB{
            val tempInstance = INSTANCE

            if(tempInstance != null){
                return tempInstance

            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    TpSeminarioApp.instance.applicationContext,
                    UserDB::class.java,
                    Constants.appDBName

                )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }


    }


}