package com.threeforcom.testexam.base

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.databinding.ViewDataBinding


@SuppressLint("Registered")
abstract class BaseActivity<VDB : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity() {
    protected var mProgressDialog: DialogProgress? = null
    protected lateinit var viewDataBinding: VDB


    abstract var viewModel: VM

    abstract fun getBindingVariable(): Int

    /**
     * @return layout resource id
     */
    @LayoutRes
    abstract fun getLayoutId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()
        performObservable()
    }

    protected fun showLoading() {
        mProgressDialog?.dismiss()
        if (mProgressDialog == null)
            mProgressDialog = DialogProgress(this)
        mProgressDialog!!.show()
    }

    protected fun hideLoading(){
        mProgressDialog?.dismiss()
    }

    private fun performDataBinding() {
        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        viewDataBinding.setVariable(getBindingVariable(), viewModel)
        viewDataBinding.executePendingBindings()
    }


    protected fun performObservable() {
        viewModel.isLoading.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                if (viewModel.isLoading.get())
                    showLoading()
                else
                    hideLoading()
            }

        })
    }
}