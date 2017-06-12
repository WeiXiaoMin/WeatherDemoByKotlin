package com.bmbm.weatherdemobykotlin.domain.model

import com.bmbm.weatherdemobykotlin.domain.Command
import com.bmbm.weatherdemobykotlin.domain.datasource.ForecastProvider

/**
 * 获取DayForecast的命令类
 * Created by sjsd on 2017/6/9.
 */
class RequestDayForecastCommand(
        val id: Long,
        val forecastProvider: ForecastProvider = ForecastProvider()) :
        Command<Forecast> {

    override fun execute(): Forecast = forecastProvider.requestForecast(id)

}