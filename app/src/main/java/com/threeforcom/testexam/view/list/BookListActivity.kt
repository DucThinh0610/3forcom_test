package com.threeforcom.testexam.view.list

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.LinearLayoutManager
import com.threeforcom.testexam.MainApplication
import com.threeforcom.testexam.R
import com.threeforcom.testexam.base.BaseActivity
import com.threeforcom.testexam.databinding.ActivityListBookBinding
import com.threeforcom.testexam.view.detail.DetailBookActivity
import com.threeforcom.testexam.view.favorite_management.FavoriteActivity
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_list_book.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class BookListActivity : BaseActivity<ActivityListBookBinding, BookViewModel>() {
    @Inject
    override lateinit var viewModel: BookViewModel
    private var disposeSearch: Disposable? = null

    lateinit var adapterBook: BookAdapter

    var isOnline = false

    override fun getBindingVariable(): Int = BR.bookVM

    override fun getLayoutId(): Int = R.layout.activity_list_book

    override fun onCreate(savedInstanceState: Bundle?) {
        MainApplication.appComponent?.inject(this)
        super.onCreate(savedInstanceState)
        isOnline = isNetworkAvailable()

        viewModel.searchBook("")
        disposeSearch = viewDataBinding.search.fromView(
            queryChangeAction = {

            },
            querySubmitAction = {

            }
        ).debounce(500, TimeUnit.MILLISECONDS).subscribe {
            viewModel.searchBook(it, !isOnline)
        }

        //
    }

    override fun initEvent() {
        ic_fa_management.setOnClickListener {//goto management favorite activity
            FavoriteActivity.startActivity(this)
        }
    }

    override fun onDestroy() {
        disposeSearch?.dispose()
        super.onDestroy()
    }

    override fun performDataBinding() {
        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        adapterBook = BookAdapter({
            if (isNetworkAvailable()) {
                DetailBookActivity.startActivity(this, it.id)
            } else {
                showToast("Need network to get the detail!!")
            }
        }, {
            viewModel.performFavoriteItem(it, viewDataBinding.search.query.isNullOrEmpty())
        })
        viewDataBinding.rcvBooks.adapter = adapterBook
        viewDataBinding.rcvBooks.layoutManager = LinearLayoutManager(this)

        viewDataBinding.setVariable(getBindingVariable(), viewModel)
        viewDataBinding.executePendingBindings()
    }

}