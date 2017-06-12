package com.bmbm.weatherdemobykotlin.domain

import com.bmbm.weatherdemobykotlin.domain.datasource.ForecastProvider
import com.bmbm.weatherdemobykotlin.domain.model.ForecastList

/**
 * 获取数据的命令类，不用关心数据从何而来
 * Created by sjsd on 2017/6/5.
 */
class RequestForecastCommand(
        val zipCode: Long,
        val forecastProvider: ForecastProvider = ForecastProvider()) :
        Command<ForecastList> {


    companion object {
        val DAYS = 7    //wxm:获取今后7天的天气预报
    }


    override fun execute(): ForecastList
            = forecastProvider.requestByZipCode(zipCode, DAYS)
}