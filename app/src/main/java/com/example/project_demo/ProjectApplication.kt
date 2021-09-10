package com.example.project_demo

import android.app.Application
import android.support.multidex.MultiDex
import com.example.project_demo.di.module.networkModule
import com.example.project_demo.di.module.repositoryModule
import com.example.project_demo.di.module.viewModelModule
import com.service.beneat.di.module.utilityModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ProjectApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        MultiDex.install(this)

        startKoin {
            androidContext(this@ProjectApplication)
            modules(arrayListOf(networkModule, utilityModule, repositoryModule, viewModelModule))
            androidLogger()
        }
    }
}