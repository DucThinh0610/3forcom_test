package com.threeforcom.testexam.di.component

import android.app.Application
import android.content.Context
import com.threeforcom.testexam.MainApplication
import com.threeforcom.testexam.di.ApplicationContext
import com.threeforcom.testexam.di.module.ApplicationModule
import com.threeforcom.testexam.di.module.BookRepoModule
import com.threeforcom.testexam.di.module.NetworkModule
import com.threeforcom.testexam.di.module.ViewModelModule
import com.threeforcom.testexam.view.list.BookListActivity
import com.threeforcom.testexam.view.detail.DetailBookActivity
import com.threeforcom.testexam.view.favorite_management.FavoriteActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class, BookRepoModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(mainApplication: MainApplication)

    fun inject(bookListActivity: BookListActivity)

    fun inject(favoriteActivity: FavoriteActivity)

    fun inject(detailBookActivity: DetailBookActivity)

    val application: Application

    @get:ApplicationContext
    val context: Context

}
