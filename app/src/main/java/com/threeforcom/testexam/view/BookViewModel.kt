package com.threeforcom.testexam.view

import android.util.Log
import androidx.databinding.ObservableField
import com.threeforcom.testexam.base.BaseViewModel
import com.threeforcom.testexam.data.local.BookEntity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class BookViewModel : BaseViewModel() {

    public var listBooks: ObservableField<List<BookEntity>> = ObservableField(arrayListOf(
        BookEntity(id = "1",name = "name",isFavorite = false),
        BookEntity(id = "2",name = "name2",isFavorite = true),
        BookEntity(id = "3",name = "name3",isFavorite = false)
    ))

    fun testLoading() {
        isLoading.set(true)
        val timerObs = Observable.timer(2, TimeUnit.SECONDS)
            .flatMap {
                return@flatMap Observable.create<String> { emitter ->
                    Log.d("TimerExample", "Create")
                    emitter.onNext("MindOrks")
                    emitter.onComplete()
                }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                print("value")
                isLoading.set(false)
            }
        compositeDisposable.add(timerObs)
    }
}