package com.threeforcom.testexam.di.module

import com.threeforcom.testexam.data.repo.BookRepository
import com.threeforcom.testexam.view.BookViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelModule {
    @Singleton
    @Provides
    fun providesBookViewModel(bookRepository: BookRepository): BookViewModel {
        return BookViewModel(bookRepository)
    }
}