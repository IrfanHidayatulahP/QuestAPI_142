package com.example.continuerestapi.widget

import android.app.Application
import com.example.continuerestapi.repository.AppContainer
import com.example.continuerestapi.repository.MahasiswaContainer

class MahasiswaApps : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = MahasiswaContainer()
    }
}