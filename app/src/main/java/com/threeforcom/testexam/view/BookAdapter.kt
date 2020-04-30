package com.threeforcom.testexam.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.threeforcom.testexam.R
import com.threeforcom.testexam.data.local.BookEntity
import com.threeforcom.testexam.databinding.ItemBookBinding
import com.threeforcom.testexam.utils.BindableAdapter


class BookAdapter(val onClickItem: ((item: BookEntity) -> Unit)) :
    RecyclerView.Adapter<BookAdapter.BookHolder>(),
    BindableAdapter<List<BookEntity>>, BookDelegate {
    var bookList = emptyList<BookEntity>()

    inner class BookHolder(private val bookBinding: ItemBookBinding) :
        RecyclerView.ViewHolder(bookBinding.root) {

        fun bind(item: BookEntity) {
            bookBinding.item = item
            bookBinding.delegate = this@BookAdapter
            bookBinding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookHolder {
        val binding: ItemBookBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_book, parent, false
            )
        return BookHolder(binding)
    }

    override fun getItemCount() = bookList.count()

    override fun onBindViewHolder(holder: BookHolder, position: Int) {
        holder.bind(bookList[position])
    }

    override fun setData(data: List<BookEntity>) {
        bookList = data
        notifyDataSetChanged()
    }

    override fun onClickBookItem(bookItem: BookEntity) {
        onClickItem(bookItem)
    }

    override fun onClickFavorite(bookItem: BookEntity) {

    }

}

interface BookDelegate {
    fun onClickBookItem(bookItem: BookEntity)

    fun onClickFavorite(bookItem: BookEntity)
}