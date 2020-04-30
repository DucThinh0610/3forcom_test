package com.threeforcom.testexam.view.detail

import androidx.databinding.ObservableField
import com.threeforcom.testexam.base.BaseViewModel
import com.threeforcom.testexam.data.repo.BookRepository
import com.threeforcom.testexam.model.BookModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class BookDetailViewModel(private val bookRepository: BookRepository) : BaseViewModel() {
    var bookDetail: ObservableField<BookModel> = ObservableField()


    fun getDetail(id: String) {
        val detailDispose = bookRepository.getDetailBook(id).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                bookDetail.set(it)
            }
        compositeDisposable.add(detailDispose)
    }
}