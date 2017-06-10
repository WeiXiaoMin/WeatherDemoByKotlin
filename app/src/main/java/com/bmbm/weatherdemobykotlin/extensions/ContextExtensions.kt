package com.bmbm.weatherdemobykotlin.extensions

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.widget.Toast

/**
 * Created by wxm on 2017/6/4.
 */
fun Context.toast(msg: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, msg, duration).show()
}

fun Activity.loadingDialog(msg: CharSequence): AlertDialog {
    return AlertDialog.Builder(this)
            .setMessage(msg)
            .create()
}