package com.bmbm.weatherdemobykotlin.domain.datasource

import com.bmbm.weatherdemobykotlin.domain.model.Forecast
import com.bmbm.weatherdemobykotlin.domain.model.ForecastList

/**
 * 从数据源中获取数据
 * Created by sjsd on 2017/6/8.
 */
interface ForecastDataSource {

    /**
     * 根据某一天返回一周的天气预报
     * @param zipCode 城市代码
     * @param date 日期
     */
    fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList?

    /**
     * 根据Forecast的id获取Forecast
     * @param Forecast的id
     */
    fun requestForecast(id: Long): Forecast?
}