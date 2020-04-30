package com.threeforcom.testexam.view.favorite_management

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.threeforcom.testexam.BR
import com.threeforcom.testexam.MainApplication
import com.threeforcom.testexam.R
import com.threeforcom.testexam.base.BaseActivity
import com.threeforcom.testexam.databinding.ActivityFavoriteBinding
import com.threeforcom.testexam.view.BookAdapter
import com.threeforcom.testexam.view.BookViewModel
import kotlinx.android.synthetic.main.activity_favorite.*
import javax.inject.Inject

class FavoriteActivity : BaseActivity<ActivityFavoriteBinding, BookViewModel>() {
    @Inject
    override lateinit var viewModel: BookViewModel

    override fun getBindingVariable() = BR.viewModel

    override fun getLayoutId(): Int = R.layout.activity_favorite

    override fun onCreate(savedInstanceState: Bundle?) {
        MainApplication.appComponent?.inject(this)
        super.onCreate(savedInstanceState)
        val adapterBook = BookAdapter({
            if (isNetworkAvailable()) {

            } else {
                showToast("Need network to get the detail!!")
            }
        }, {
            viewModel.performFavoriteItem(it, true)
        })
        viewDataBinding.rcvBooks.adapter = adapterBook
        viewDataBinding.rcvBooks.layoutManager = LinearLayoutManager(this)
        viewModel.searchBook("", false)
    }

    override fun initEvent() {
        imv_back.setOnClickListener { finish() }
    }

    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, FavoriteActivity::class.java))
        }
    }
}