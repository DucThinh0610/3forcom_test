package com.threeforcom.testexam.utils

import android.net.Uri
import androidx.annotation.DrawableRes
import android.widget.ImageView

import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

import com.bumptech.glide.load.DecodeFormat.PREFER_ARGB_8888
import com.threeforcom.testexam.R

object ImageLoader {
    fun load(
        url: String?,
        @DrawableRes defaultImage: Int? = null,
        @DrawableRes imageError: Int? = null,
        imageView: ImageView
    ) {
        val requestOptions = RequestOptions()
            .centerCrop()
            .placeholder(defaultImage ?: R.mipmap.ic_launcher)
            .error(imageError ?: 0)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .format(PREFER_ARGB_8888)
        Glide.with(imageView.context)
            .asBitmap()
            .apply(requestOptions)
            .load(url)
            .into(imageView)
    }

    fun load(uri: Uri, imageView: ImageView) {
        val requestOptions = RequestOptions()
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .format(PREFER_ARGB_8888)
        Glide.with(imageView.context)
            .asBitmap()
            .apply(requestOptions)
            .load(uri)
            .into(imageView)
    }

    fun load(url: String?, imageView: ImageView) {
        if (url == null) {
            return
        }
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        val requestOptions = RequestOptions()
            .centerCrop()
            .placeholder(R.drawable.ic_image_hover)
            .error(R.drawable.imv_error_loading)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .format(PREFER_ARGB_8888)
        Glide.with(imageView.context)
            .asBitmap()
            .apply(requestOptions)
            .load(url)
            .into(imageView)
    }

    fun load(
        @DrawableRes drawable: Int,
        imageView: ImageView,
        scaleType: ImageView.ScaleType? = ImageView.ScaleType.CENTER_CROP
    ) {
        imageView.scaleType = scaleType
        val requestOptions = RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        Glide.with(imageView.context)
            .asBitmap()
            .apply(requestOptions)
            .load(drawable)
            .into(imageView)
    }
}
