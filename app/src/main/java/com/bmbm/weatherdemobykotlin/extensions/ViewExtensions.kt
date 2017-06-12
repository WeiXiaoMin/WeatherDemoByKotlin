package com.bmbm.weatherdemobykotlin.extensions

import android.content.Context
import android.support.v4.widget.TextViewCompat
import android.view.View
import android.widget.TextView
import com.bmbm.weatherdemobykotlin.domain.model.Forecast

/**
 * Created by sjsd on 2017/6/6.
 */
val View.ctx: Context
    get() = context


interface OnItemClickListener {
    operator fun invoke(forecast: Forecast)
}

fun TextView.setAppearance(resId: Int){
    // wxm:support v4 包方法
    TextViewCompat.setTextAppearance(this,resId)
}

fun View.slideExit(){
    if (translationY==0f) animate().translationY(-height.toFloat())
}

fun View.slideEnter(){
    if (translationY<0f) animate().translationY(0f)
}