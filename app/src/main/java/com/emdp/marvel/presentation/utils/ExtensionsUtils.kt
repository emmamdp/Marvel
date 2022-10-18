package com.emdp.marvel.presentation.utils

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

fun View.isShow(show: Boolean) {
    this.visibility = if (show) View.VISIBLE else View.GONE
}

fun Context.glide(imageLoad: String, placeholder: Int, imageError: Int, imageView: ImageView) {
    Glide.with(this)
        .load(imageLoad)
        .placeholder(placeholder)
        .error(imageError)
        .into(imageView)
}