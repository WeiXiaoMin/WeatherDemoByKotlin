package com.bmbm.weatherdemobykotlin.domain.datasource

import com.bmbm.weatherdemobykotlin.data.db.ForecastDb
import com.bmbm.weatherdemobykotlin.data.server.ForecastServer
import com.bmbm.weatherdemobykotlin.domain.model.Forecast
import com.bmbm.weatherdemobykotlin.domain.model.ForecastList
import com.bmbm.weatherdemobykotlin.extensions.firstResult

/**
 * 管理所有已知的数据源：数据库、服务器
 * Created by sjsd on 2017/6/8.
 */
class ForecastProvider(val source: List<ForecastDataSource> = ForecastProvider.SOURCE) {

    companion object {
        val DAY_IN_MILLIS = 1000 * 60 * 60 * 24 //wxm:一天的毫秒数
        val SOURCE by lazy { listOf(ForecastDb(), ForecastServer()) }   //wxm:按集合的顺序，先从数据库中获取数据，在从服务器
    }

    /**
     * 根据城市代码获取今后几天的天气预报
     * @param zipCode 城市代码
     * @param days 今后几天
     */
    fun requestByZipCode(zipCode: Long, days: Int): ForecastList = requestToSources {
        val forecastList = it.requestForecastByZipCode(zipCode, todayTimeSpan())
        if (forecastList != null && forecastList.size() >= days) forecastList else null
    }

    /**
     * 根据Forecast的id查询某天的天气预报
     * @param id Forecast的id
     */
    fun requestForecast(id: Long): Forecast = requestToSources { it.requestForecast(id) }

    //wxm:?? 将毫秒粗略到天
    private fun todayTimeSpan() = System.currentTimeMillis() / DAY_IN_MILLIS * DAY_IN_MILLIS

    //wxm:遍历数据源集合，获取第一个不为空的结果
    private fun <T> requestToSources(f: (ForecastDataSource) -> T?): T
            = source.firstResult { f(it) }
}