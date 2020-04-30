package com.threeforcom.testexam.base

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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
        viewModel.onCreate()
        performDataBinding()
        performObservable()
        initEvent()
    }

    open fun initEvent(){

    }

    protected fun showLoading() {
        mProgressDialog?.dismiss()
        if (mProgressDialog == null)
            mProgressDialog = DialogProgress(this)
        mProgressDialog!!.show()
    }

    protected fun hideLoading() {
        mProgressDialog?.dismiss()
    }

    open fun performDataBinding() {
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

    fun showToast(mess: String) {
        Toast.makeText(this, mess, Toast.LENGTH_SHORT).show()
    }

    fun showToast(res: Int, isDebug: Boolean? = false) {
        Toast.makeText(this, getString(res), Toast.LENGTH_SHORT).show()
        if (isDebug == true)
            Log.e("", getString(res))
    }

    fun isNetworkAvailable(): Boolean {
        val connMgr = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo? = connMgr.activeNetworkInfo
        return networkInfo?.isConnected == true
    }
}