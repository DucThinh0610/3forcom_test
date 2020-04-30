package com.threeforcom.testexam.view.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.threeforcom.testexam.BR
import com.threeforcom.testexam.MainApplication
import com.threeforcom.testexam.R
import com.threeforcom.testexam.base.BaseActivity
import com.threeforcom.testexam.databinding.ActivityDetailBookBinding
import kotlinx.android.synthetic.main.activity_detail_book.*
import javax.inject.Inject

class DetailBookActivity : BaseActivity<ActivityDetailBookBinding, BookDetailViewModel>() {
    @Inject
    override lateinit var viewModel: BookDetailViewModel

    override fun getBindingVariable(): Int = BR.detailVM

    override fun getLayoutId(): Int = R.layout.activity_detail_book

    override fun onCreate(savedInstanceState: Bundle?) {
        MainApplication.appComponent?.inject(this)
        super.onCreate(savedInstanceState)
        intent.getStringExtra("KEY_ID")?.let {
            viewModel.getDetail(it)
        } ?: kotlin.run {
            showToast("Error when get detail")
            finish()
        }
        imv_back.setOnClickListener { finish() }
    }

    companion object {
        fun startActivity(context: Context, id: String) {
            val intent = Intent(context, DetailBookActivity::class.java)
            intent.putExtra("KEY_ID", id)
            context.startActivity(intent)
        }
    }
}