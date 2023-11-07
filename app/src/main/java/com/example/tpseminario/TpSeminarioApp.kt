package com.example.tpseminario

import android.app.Application

class TpSeminarioApp: Application() {

    companion object {
        lateinit var instance: TpSeminarioApp
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}