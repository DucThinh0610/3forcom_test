package com.threeforcom.testexam.base

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {
    protected var compositeDisposable = CompositeDisposable()
    public var isLoading = ObservableBoolean()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    open fun onCreate(){

    }
}