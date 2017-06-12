package com.bmbm.weatherdemobykotlin

import android.app.Application
import com.bmbm.weatherdemobykotlin.extensions.NotNullSingleValueVar

/**
 *
 * Created by sjsd on 2017/6/7.
 */
class App : Application() {

    companion object {

        //wxm:该方法在Java中可以这么用App.Companion.getInstance(),var属性默认含get和set方法，val默认含get方法
        //wxm:该自定义委托NotNullSingleValueVar只允许赋值一次，即不允许外部改变instance的值，其实可以通过私有化set方法达到目的
        var instance: Application by NotNullSingleValueVar()
        private set //wxm:私有了instance的set方法

        //wxm:使用了@JvmStatic注，这是一个真正的静态方法，在Java中可以这么用App.instance()
//        @JvmStatic fun instance() = instance

        //wxm:该方法在Java中可以这么用App.Companion.instance()
//        fun instance() = instance
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}