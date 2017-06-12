package com.bmbm.weatherdemobykotlin.extensions

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.support.v4.content.ContextCompat
import android.util.DisplayMetrics
import android.util.TypedValue
import org.jetbrains.anko.displayMetrics

/**
 * Created by wxm on 2017/6/4.
 */
//fun Context.toast(msg: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
//    Toast.makeText(this, msg, duration).show()
//}

fun Context.getContext() = this

fun Activity.loadingDialog(msg: CharSequence): AlertDialog {
    return AlertDialog.Builder(this)
            .setMessage(msg)
            .create()
}

fun Context.typedValue(resId: Int, typedValue: TypedValue = TypedValue()): TypedValue {
    theme.resolveAttribute(resId, typedValue, true)
    return typedValue
}

fun Context.typedValueDimen(resId: Int, dm: DisplayMetrics = displayMetrics): Int {
    return TypedValue.complexToDimensionPixelSize(typedValue(resId).data, dm)
}

fun Context.dpSize(dp: Float, dm: DisplayMetrics = displayMetrics): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, dm)
}

fun Context.dpSizeInt(dp: Float, dm: DisplayMetrics = displayMetrics): Int {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, dm).toInt()
}

fun Context.color(resId: Int):Int=ContextCompat.getColor(this,resId)