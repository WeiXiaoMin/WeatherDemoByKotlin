package com.bmbm.weatherdemobykotlin.domain.model

/**
 * Created by wxm on 2017/6/4.
 */
data class ForecastList(val id: Long, val city: String, val country: String, val dailyForecast: List<Forecast>) {

    //wxm:重载a[i]操作符
    operator fun get(postion: Int) = dailyForecast[postion] //wxm:省略返回值类型，编译器自动推断

    fun size(): Int = dailyForecast.size
}

data class Forecast(val id: Long, val date: Long, val description: String, val high: Int, val low: Int, val iconUrl: String)