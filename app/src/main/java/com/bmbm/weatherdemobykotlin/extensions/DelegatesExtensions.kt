package com.bmbm.weatherdemobykotlin.extensions

import android.content.Context
import android.content.SharedPreferences
import com.bmbm.weatherdemobykotlin.App
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * 只赋值一次，并且返回非空值，多次赋值则抛异常
 * 用于需要延迟赋值并且返回非空值的属性，以便使用该属性时不需要进行非空检查
 * Created by sjsd on 2017/6/7.
 */
class NotNullSingleValueVar<T> : ReadWriteProperty<Any?, T> {
    private var value: T? = null
    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return this.value ?: throw IllegalStateException("${property.name}is initialized")
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        this.value = if (this.value == null) value else throw  IllegalStateException("${property.name} already initialized")
    }

}


class LongPreferences(val context: Context = App.instance, val key: String, val defult: Long)
    : ReadWriteProperty<Any?, Long> {

    val prefs:SharedPreferences by lazy { context.getSharedPreferences("defult",Context.MODE_PRIVATE) }

    override fun getValue(thisRef: Any?, property: KProperty<*>): Long {
       return prefs.getLong(key,defult)
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Long) {
        prefs.edit().putLong(key,value).apply()
    }

}