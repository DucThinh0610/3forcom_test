package com.threeforcom.testexam.view.list

import android.util.Log
import androidx.databinding.ObservableField
import com.threeforcom.testexam.base.BaseViewModel
import com.threeforcom.testexam.data.local.BookEntity
import com.threeforcom.testexam.data.repo.BookRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class BookViewModel(private val bookRepository: BookRepository) : BaseViewModel() {

    var listBooks: ObservableField<List<BookEntity>> = ObservableField(arrayListOf())

//    fun testLoading() {
//        isLoading.set(true)
//        val timerObs = Observable.timer(2, TimeUnit.SECONDS)
//            .flatMap {
//                return@flatMap Observable.create<String> { emitter ->
//                    Log.d("TimerExample", "Create")
//                    emitter.onNext("MindOrks")
//                    emitter.onComplete()
//                }
//            }
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe {
//                print("value")
//                isLoading.set(false)
//            }
//        compositeDisposable.add(timerObs)
//    }

    fun searchBook(searchKey: String, isLocalSearch: Boolean = true) {
        val searchDispose =
            bookRepository.searchBook(searchKey, isLocalSearch || searchKey.isEmpty())
                .firstElement()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { value ->
                        Log.d("searchBook", "onNext ${value.count()}")
                        listBooks.set(value)
                    },
                    { error ->
                        Log.d("searchBook", "onNext ${error.message}")
                    }, {

                    }
                )
        compositeDisposable.add(searchDispose)
    }

    fun performFavoriteItem(bookEntity: BookEntity, willRemoveInList: Boolean = false) {
        val bookUpdateDispose =
            bookRepository.updateFavoriteBook(bookEntity).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { newItem ->
                    var currentList =
                        listBooks.get()?.toMutableList()
                    if (!willRemoveInList) {
                        currentList = currentList?.map { eItem ->
                            if (eItem.id == newItem.id)
                                newItem
                            else
                                eItem
                        }?.toList()?.toMutableList()
                    } else {
                        currentList?.remove(bookEntity)
                    }
                    listBooks.set(currentList)
                }
        compositeDisposable.add(bookUpdateDispose)
    }
}