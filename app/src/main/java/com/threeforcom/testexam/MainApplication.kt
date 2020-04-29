package com.threeforcom.testexam

import android.app.Application
import com.threeforcom.testexam.di.component.ApplicationComponent
import com.threeforcom.testexam.di.component.DaggerApplicationComponent
import com.threeforcom.testexam.di.module.ApplicationModule
import com.threeforcom.testexam.di.module.NetworkModule

class MainApplication : Application() {
    companion object {
        var appComponent: ApplicationComponent? = null
            private set
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .networkModule(NetworkModule())
            .build()
    }
}