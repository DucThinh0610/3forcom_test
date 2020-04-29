package com.threeforcom.testexam.view

import androidx.databinding.library.baseAdapters.BR
import com.threeforcom.testexam.R
import com.threeforcom.testexam.base.BaseActivity
import com.threeforcom.testexam.databinding.ActivityListBookBinding

class BookListActivity : BaseActivity<ActivityListBookBinding, BookViewModel>() {
    override var viewModel: BookViewModel = BookViewModel()

    override fun getBindingVariable(): Int = BR.bookVM

    override fun getLayoutId(): Int = R.layout.activity_list_book
}