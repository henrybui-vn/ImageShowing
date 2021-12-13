package com.android.imageshowing

import android.app.Application
import com.android.imageshowing.di.apiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(listOf(apiModule))
        }
    }
}