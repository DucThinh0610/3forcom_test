package com.threeforcom.testexam.view

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.LinearLayoutManager
import com.threeforcom.testexam.R
import com.threeforcom.testexam.base.BaseActivity
import com.threeforcom.testexam.databinding.ActivityListBookBinding

class BookListActivity : BaseActivity<ActivityListBookBinding, BookViewModel>() {
    override var viewModel: BookViewModel = BookViewModel()

    override fun getBindingVariable(): Int = BR.bookVM

    override fun getLayoutId(): Int = R.layout.activity_list_book

    lateinit var adapterBook: BookAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun performDataBinding() {
        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        adapterBook = BookAdapter {
            print("value click ${it.name}")
        }
        viewDataBinding.rcvBooks.adapter = adapterBook
        viewDataBinding.rcvBooks.layoutManager = LinearLayoutManager(this)

        viewDataBinding.setVariable(getBindingVariable(), viewModel)
        viewDataBinding.executePendingBindings()
    }

}