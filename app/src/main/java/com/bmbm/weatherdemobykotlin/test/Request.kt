package com.bmbm.weatherdemobykotlin.test

import android.util.Log
import java.net.URL

/**
 * Created by wxm on 2017/6/4.
 */
 class Request(val url: String) {
     fun run() {
        val forecastJsonStr = URL(url).readText()
        Log.d(javaClass.simpleName, forecastJsonStr)
    }
}