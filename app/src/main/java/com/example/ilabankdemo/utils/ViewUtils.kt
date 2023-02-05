package com.example.ilabankdemo.utils

import android.content.Context
import android.view.View
import android.widget.Toast

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}


fun View.showView() {
    visibility = View.VISIBLE
}

fun View.hideView() {
    visibility = View.GONE
}

fun View.invisibleView() {
    visibility = View.INVISIBLE
}

