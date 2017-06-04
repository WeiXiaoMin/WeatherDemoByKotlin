package com.bmbm.weatherdemobykotlin.domain.model

/**
 * Created by wxm on 2017/6/4.
 */
data class ForecastList(val city: String, val country: String, val dailyForecast: List<Forecast>)

data class Forecast(val date: String, val description: String, val high: Int, val low: Int)