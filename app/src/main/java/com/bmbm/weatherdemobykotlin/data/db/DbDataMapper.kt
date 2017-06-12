package com.bmbm.weatherdemobykotlin.data.db

import com.bmbm.weatherdemobykotlin.domain.model.Forecast
import com.bmbm.weatherdemobykotlin.domain.model.ForecastList

/**
 * Created by sjsd on 2017/6/7.
 */
class DbDataMapper {

    fun converFromDomain(forecast: ForecastList): CityForecast = with(forecast) {
        val df = dailyForecast.map { convertDayFromDomain(id, it) }
        CityForecast(id, city, country, df)
    }

    fun converToDomain(forecast: CityForecast): ForecastList = with(forecast) {
        val df = dailyForecast.map { convertDayToDomain(it) }
        ForecastList(_id, city, country, df)
    }

    fun convertDayFromDomain(cityId: Long, forecast: Forecast): DayForecast = with(forecast) {
        DayForecast(date, description, high, low, iconUrl, cityId)
    }

    fun convertDayToDomain(forecast: DayForecast): Forecast = with(forecast) {
        Forecast(_id, date, description, high, low, iconUrl)
    }
}