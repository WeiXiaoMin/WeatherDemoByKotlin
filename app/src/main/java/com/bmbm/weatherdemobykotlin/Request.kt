package com.bmbm.weatherdemobykotlin

import android.util.Log
import java.net.URL

/**
 * Created by wxm on 2017/6/4.
 */
public class Request(val url: String) {
    public fun run() {
        val forecastJsonStr = URL(url).readText()
        Log.d(javaClass.simpleName, forecastJsonStr)
    }
}