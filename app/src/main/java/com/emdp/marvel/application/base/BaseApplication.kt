package com.emdp.marvel.application.base

import android.app.Application
import com.emdp.data.di.dataModule
import com.emdp.domain.di.domainModule
import com.emdp.marvel.presentation.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BaseApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@BaseApplication)
            modules(listOf(presentationModule, domainModule, dataModule))
        }
    }
}