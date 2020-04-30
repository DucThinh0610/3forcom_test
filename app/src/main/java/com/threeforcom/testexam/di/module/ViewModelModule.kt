package com.threeforcom.testexam.di.module

import com.threeforcom.testexam.data.repo.BookRepository
import com.threeforcom.testexam.view.list.BookViewModel
import com.threeforcom.testexam.view.detail.BookDetailViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelModule {

    @Provides
    fun providesBookViewModel(bookRepository: BookRepository): BookViewModel {
        return BookViewModel(bookRepository)
    }

    @Singleton
    @Provides
    fun providesBookDetailViewModel(bookRepository: BookRepository): BookDetailViewModel {
        return BookDetailViewModel(bookRepository)
    }


}