package com.bmbm.weatherdemobykotlin.data.db

import com.bmbm.weatherdemobykotlin.domain.datasource.ForecastDataSource
import com.bmbm.weatherdemobykotlin.domain.model.Forecast
import com.bmbm.weatherdemobykotlin.domain.model.ForecastList
import com.bmbm.weatherdemobykotlin.extensions.*
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import java.util.*
import org.jetbrains.anko.db.delete as clear

/**
 *  数据库操作类：获取和保存
 * Created by sjsd on 2017/6/7.
 */
class ForecastDb(val forecastDbHelper: ForecastDbHelper = ForecastDbHelper(),
                 val dbDataMapper: DbDataMapper = DbDataMapper())
    : ForecastDataSource {

    override fun requestForecast(id: Long): Forecast? = forecastDbHelper.use {

        val dayForecast: DayForecast? = select(DayForecastTable.NAME)   //wxm:SQLiteDatabase的扩展方法，返回SelectQueryBuilder
//                .whereSimple("${DayForecastTable.ID} = ?", id.toString())   //wxm:返回SelectQueryBuilder
                .byId(id)   //wxm:返回SelectQueryBuilder
                .parseOpt { DayForecast(HashMap(it)) } //wxm:解析器MapRowParser转换成model（DayForecast?）

        dayForecast?.let { dbDataMapper.convertDayToDomain(it) }
    }

    /**
     * 从数据库中获取ForecastList对象
     * 虽然函数表达式不需要显式声明返回类型，但为了方便阅读，不妨显式地声明
     * @param zipCode 城市代码
     * @param date 日期
     * @return ForecastList?
     */
    override fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList? = forecastDbHelper.use {
        //wxm:use函数封装了打开数据库和关闭数据库的操作，并返回一个对象

        //wxm:select是SQLiteDatabase对象的方法，在use函数块中可以直接使用。该函数块处于SQLiteDatabase的对象之中
        val dailyForecast = select(DayForecastTable.NAME)
                .whereSimple("${DayForecastTable.CITY_ID} = ? AND ${DayForecastTable.DATE} >= ?", //wxm:whereSimple使用“?”做标记
                        zipCode.toString(), date.toString())
                .parseList { DayForecast(HashMap(it)) }

        val cityForecast = select(CityForecastTable.NAME)
                .whereArgs("${CityForecastTable.ID} = {id}", "id" to zipCode.toString())    //wxm:where使用String字段做标记（tag）
                .parseOpt { CityForecast(HashMap(it), dailyForecast) }

        //wxm:lambda函数返回最后一行代码表示的值，这里返回ForecastList
//        if (cityForecast != null) dbDataMapper.converToDomain(cityForecast) else null

        logd("requestForecastByZipCode")

        //wxm:let函数类型转换 (T)->R ，it指调用的对象
        cityForecast?.let { dbDataMapper.converToDomain(it) }
    }


    /**
     * 保存一个ForecastList到数据库中
     * @param forecast ForecastList对象
     * @return Unit
     */
    fun saveForecast(forecast: ForecastList): Unit = forecastDbHelper.use {

        //wxm:delete的别名，避免扩展方法和成员方法冲突（其实由于传参的要求不同，两者并不会冲突，是为方法的重载。这可作为一个取别名的演示）
        clear(DayForecastTable.NAME)
        clear(CityForecastTable.NAME)

        with(dbDataMapper.converFromDomain(forecast)) {

            //wxm:“*”号是将Array类型对象分解成vararg参数（可变长度参数，类似于Java的T ...t）
            insert(CityForecastTable.NAME, *map.toVarargArray())

            dailyForecast.forEach { insert(DayForecastTable.NAME, *it.map.toVarargArray()) }
        }
    }

}