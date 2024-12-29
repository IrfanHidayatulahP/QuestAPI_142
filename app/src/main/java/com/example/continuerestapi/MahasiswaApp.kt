package com.example.continuerestapi

import android.app.Application
import com.example.continuerestapi.repository.AppContainer
import com.example.continuerestapi.repository.MahasiswaContainer

class MahasiswaApp : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = MahasiswaContainer()
    }
}