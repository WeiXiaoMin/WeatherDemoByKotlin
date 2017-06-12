package com.bmbm.weatherdemobykotlin.extensions

import android.util.Log

/**
 * Created by wxm on 2017/6/11.
 */
fun Any.logv( msg: String,tag: String = javaClass.simpleName) {
    Log.v(tag, msg)
}
fun Any.logd( msg: String,tag: String = javaClass.simpleName) {
    Log.d(tag, msg)
}
fun Any.logi( msg: String,tag: String = javaClass.simpleName) {
    Log.i(tag, msg)
}
fun Any.logw(msg: String,tag: String = javaClass.simpleName) {
    Log.w(tag, msg)
}
fun Any.loge(msg: String,tag: String = javaClass.simpleName) {
    Log.e(tag, msg)
}

