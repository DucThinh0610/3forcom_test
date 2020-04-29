package com.threeforcom.testexam.di.module

import com.threeforcom.testexam.data.local.BookDAO
import com.threeforcom.testexam.data.repo.BookRepository
import com.threeforcom.testexam.data.rest.ApiServices
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class BookRepoModule {
    @Singleton
    @Provides
    fun providesBookRepository(apiServices: ApiServices, bookDAO: BookDAO): BookRepository {
        return BookRepository(apiServices, bookDAO)
    }
}