package com.bmbm.weatherdemobykotlin.extensions

import java.text.DateFormat
import java.util.*

/**
 * Created by wxm on 2017/6/11.
 */
fun Long.toDateString(dateFormat: Int=DateFormat.MEDIUM):String{
    val df=DateFormat.getDateInstance(dateFormat, Locale.getDefault())
    return df.format(this)
}