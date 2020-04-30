package com.threeforcom.testexam.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

interface BindableAdapter<T> {
    fun setData(data: T)
}

@BindingAdapter("rcv_data")
fun <T> setRecyclerViewData(recyclerView: RecyclerView, data: T) {
    if (recyclerView.adapter is BindableAdapter<*>) {
        (recyclerView.adapter as BindableAdapter<T>).setData(data = data)
    }
}

@BindingAdapter("imv_url")
fun loadImageFromURL(imageView: ImageView, url: String?) {
    ImageLoader.load(url, imageView)
}