package com.threeforcom.testexam.view

import android.util.Log
import com.threeforcom.testexam.base.BaseViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class BookViewModel : BaseViewModel() {
    fun testLoading() {
        isLoading.set(true)
        val timerObs = Observable.timer(2,TimeUnit.SECONDS)
            .flatMap { return@flatMap Observable.create<String> { emitter ->
                Log.d("TimerExample", "Create")
                emitter.onNext("MindOrks")
                emitter.onComplete()
            } }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{
                print("value")
                isLoading.set(false)
            }
        compositeDisposable.add(timerObs)
    }
}