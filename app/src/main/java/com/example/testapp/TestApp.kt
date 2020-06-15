package com.example.testapp

import android.app.Application
import com.example.testapp.utils.di.viewModelModule
import org.koin.core.context.startKoin

class TestApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(viewModelModule))
        }
    }
}