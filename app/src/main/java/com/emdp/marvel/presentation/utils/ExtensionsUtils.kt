package com.emdp.marvel.presentation.utils

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar

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

fun Context.glide(imageLoad: Int, placeholder: Int, imageError: Int, imageView: ImageView) {
    Glide.with(this)
        .load(imageLoad)
        .placeholder(placeholder)
        .error(imageError)
        .into(imageView)
}

fun View.showSnackbar(
    text: String,
    textAction: String,
    duration: Int = Snackbar.LENGTH_SHORT,
    backgroundColor: Int = Color.WHITE,
    textColor: Int = Color.BLACK
) {
    val snackbar = Snackbar
        .make(this, text, duration)
        .setAction(textAction, null)
        .setTextColor(textColor)
        .setTextMaxLines(5)

    snackbar.view.apply {
        setBackgroundColor(backgroundColor)
        val params = layoutParams as CoordinatorLayout.LayoutParams
        params.apply {
            gravity = Gravity.TOP
            topMargin = 280
        }
        layoutParams = params
    }

    snackbar.show()
}