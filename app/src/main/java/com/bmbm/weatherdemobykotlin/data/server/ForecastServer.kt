package com.bmbm.weatherdemobykotlin.data.server

import com.bmbm.weatherdemobykotlin.data.db.ForecastDb
import com.bmbm.weatherdemobykotlin.domain.datasource.ForecastDataSource
import com.bmbm.weatherdemobykotlin.domain.model.Forecast
import com.bmbm.weatherdemobykotlin.domain.model.ForecastList
import com.bmbm.weatherdemobykotlin.extensions.logd

/**
 * 从网络服务器中获取数据源
 * Created by sjsd on 2017/6/8.
 */
class ForecastServer(val serverDataMapper: ServerDataMapper = ServerDataMapper(),
                     val forecastDb: ForecastDb = ForecastDb()) : ForecastDataSource {

    //wxm:不支持该操作
    override fun requestForecast(id: Long): Forecast = throw UnsupportedOperationException()

    override fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList? {
        val result: ForecastResult = ForecastRequest(zipCode).execute()
        val convert = serverDataMapper.convertToDomain(zipCode, result)
        forecastDb.saveForecast(convert)

        logd("requestForecastByZipCode")

        return forecastDb.requestForecastByZipCode(zipCode, date)
    }
}