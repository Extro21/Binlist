package com.example.binlist

import android.app.Application
import com.example.binlist.di.baseModule
import com.example.binlist.di.dataModule
import com.example.binlist.di.domainModule
import com.example.binlist.di.viewModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                viewModule, dataModule, domainModule, baseModule
            )
        }
    }

}