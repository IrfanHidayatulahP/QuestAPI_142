package com.example.continuerestapi.repository

import android.app.Application

class MahasiswaApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = MahasiswaContainer()
    }
}