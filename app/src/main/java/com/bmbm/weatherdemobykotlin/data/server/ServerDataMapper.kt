package com.bmbm.weatherdemobykotlin.data.server

import com.bmbm.weatherdemobykotlin.domain.model.ForecastList
import java.util.*
import java.util.concurrent.TimeUnit
import com.bmbm.weatherdemobykotlin.domain.model.Forecast as ModelForecast

/**
 * ServerClasses和DomainClasses互转
 * Created by sjsd on 2017/6/9.
 */
class ServerDataMapper {

    /**
     * ForecastResult映射为ForecastList
     * @param zipCode 城市代码
     * @param forecastResult 服务器返回结果
     * @return ForecastList
     */
    fun convertToDomain(zipCode: Long, forecastResult: ForecastResult): ForecastList = with(forecastResult) {
        ForecastList(zipCode, city.name, city.country, convertForecastListToDomain(list))
    }

    //wxm:ServerClasses中的List<Forecast>映射为DomainClasses中的List<Forecast>
    private fun convertForecastListToDomain(list: List<Forecast>): List<ModelForecast> {

        return list.mapIndexed { i, forecast -> //wxm:遍历并传入下标及对应的元素
            val dt = Calendar.getInstance().timeInMillis + TimeUnit.DAYS.toMillis(i.toLong())   //wxm:从当前时间每向后推移一天的毫秒数
            convertForecastItemToDomain(forecast.copy(dt = dt)) //wxm:复制一份forecast并修改dt属性（data类型属性值是不可变的）
        }
    }

    //wxm:ServerClasses中的Forecast映射为DomainClasses中的Forecast
    private fun convertForecastItemToDomain(forecast: Forecast): ModelForecast = with(forecast) {

        //wxm:id会在存入数据库后自增长，所以赋一个默认值即可
        ModelForecast(-1, dt, weather[0].description, temp.max.toInt(), temp.min.toInt(),
                generateIconUrl(weather[0].icon))
    }

    //wxm:补全url
    private fun generateIconUrl(icon: String): String = "http://openweathermap.org/img/w/$icon.png"
}