package com.ahmed.mvvmexample

import android.app.Application
import android.util.Log
import com.ahmed.mvvmexample.di.appModules
import org.koin.core.context.startKoin

class MVVMApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(appModules)
        }

        Log.d("MVVMAPP", "application onCreate()")
    }
}