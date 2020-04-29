package com.threeforcom.testexam.di.component

import android.app.Application
import android.content.Context
import com.threeforcom.testexam.MainApplication
import com.threeforcom.testexam.di.ApplicationContext
import com.threeforcom.testexam.di.module.ApplicationModule
import com.threeforcom.testexam.di.module.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class])
interface ApplicationComponent {
    fun inject(mainApplication: MainApplication)

    val application: Application

    @get:ApplicationContext
    val context: Context

}
