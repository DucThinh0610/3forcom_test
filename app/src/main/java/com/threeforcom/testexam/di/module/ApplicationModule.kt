package com.threeforcom.testexam.di.module


import android.app.Application
import android.content.Context
import androidx.room.Room
import com.threeforcom.testexam.data.local.AppDatabase
import com.threeforcom.testexam.data.local.BookDAO
import com.threeforcom.testexam.di.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val mApplication: Application) {

    var roomDatabase: AppDatabase =
        Room.databaseBuilder(mApplication, AppDatabase::class.java, "book.db")
            .allowMainThreadQueries().build()

    @Provides
    @Singleton
    internal fun provideApplication(): Application {
        return mApplication
    }

    @Provides
    @ApplicationContext
    internal fun provideContext(): Context {
        return mApplication
    }

    @Provides
    @Singleton
    internal fun provideAppDatabase(): AppDatabase {
        return roomDatabase
    }

    @Provides
    @Singleton
    internal fun provideBookDAO(): BookDAO {
        return roomDatabase.userDao()
    }

}
