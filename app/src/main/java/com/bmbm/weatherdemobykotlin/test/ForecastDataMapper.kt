package com.bmbm.weatherdemobykotlin.test

import java.text.DateFormat
import java.util.*
import com.bmbm.weatherdemobykotlin.domain.model.Forecast as ModelForecast

/**
 * 数据转换
 * Created by wxm on 2017/6/4.
 */
class ForecastDataMapper {
//    fun convertFromDataModel(forecast: ForecastResult): ForecastList {
//        return ForecastList(forecast.city.name, forecast.city.country,
//                convertForecastListToDomain(forecast.list))
//    }

//    fun convertForecastListToDomain(list: List<Forecast>): List<ModelForecast> {
//        return list.map { convertForecastItemToDomain(it) }
//    }

//    fun convertForecastItemToDomain(forecast: Forecast): ModelForecast {
//        return ModelForecast(convertDate(forecast.dt), forecast.weather[0].description
//                , forecast.temp.max.toInt(), forecast.temp.min.toInt(),generateIconUrl(forecast.weather[0].icon))
//    }

    fun convertDate(date: Long): String {
        val df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
        return df.format(date * 1000)
    }

    fun generateIconUrl(iconCode: String):String =	"http://openweathermap.org/img/w/$iconCode.png"
}