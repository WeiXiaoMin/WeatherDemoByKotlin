package com.bmbm.weatherdemobykotlin.data.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.bmbm.weatherdemobykotlin.App
import com.bmbm.weatherdemobykotlin.extensions.logd
import org.jetbrains.anko.db.*

/**
 * 数据库的创建和升级管理
 * Created by sjsd on 2017/6/7.
 *
 * 基类参数
 * 1. 上下文，不涉及ui，使用applicationContext即可
 * 2. 数据库名
 * 3. 游标工厂
 * 4. 数据库的版本号
 */
class ForecastDbHelper(ctx: Context = App.instance) : ManagedSQLiteOpenHelper(ctx, DB_NAME, null, DB_VERSION) {

    companion object {
        val DB_NAME = "forecast.db"
        val DB_VERSION = 1
        val instance: ForecastDbHelper by lazy { ForecastDbHelper() }   //wxm:懒汉式单例，lazy函数是线程安全的
    }

    //wxm:创建表
    override fun onCreate(db: SQLiteDatabase) {

        /*
         * 第一个参数是表的名称
         * 第二个参数，当是true的时候，创建之前会检查这个表是否存在。
         * 第三个参数是一个Pair类型的 vararg参数。
         *  vararg也存在在Java中，这是一种在一个函数中传入联系很多相同类型的参数。这个函数也接收一个对象数组。
         * INTEGER、TEXT、PRIMARY_KEY是anko框架中的SqlType类型
         */

//        db.createTable(CityForecastTable.NAME, true,
//                Pair(CityForecastTable.ID, INTEGER+ PRIMARY_KEY),
//                Pair(CityForecastTable.CITY, TEXT),
//                Pair(CityForecastTable.ID, TEXT))


        //wxm:使用重载的操作符“+”
        db.createTable(CityForecastTable.NAME, true,
                CityForecastTable.ID to INTEGER + PRIMARY_KEY,
                CityForecastTable.CITY to TEXT,
                CityForecastTable.COUNTRY to TEXT)

        logd("AUTOINCREMENT = $AUTOINCREMENT")

//        // wxm:莫名bug：AUTOINCREMENT编译成其引用而非其保存的值
//        db.createTable(DayForecastTable.NAME, true,
//                DayForecastTable.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,   //wxm:整型，主键，自增长
//                DayForecastTable.DATE to INTEGER,
//                DayForecastTable.DESCRIPTION to TEXT,
//                DayForecastTable.HIGH to INTEGER,
//                DayForecastTable.LOW to INTEGER,
//                DayForecastTable.ICON_URL to TEXT,
//                DayForecastTable.CITY_ID to INTEGER)


        // wxm:使用sql语句
        with(DayForecastTable) {
            db.execSQL("CREATE TABLE IF NOT EXISTS `" + NAME + "`(" +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    DATE + " INTEGER, " +
                    DESCRIPTION + " TEXT, high INTEGER, " +
                    LOW + " INTEGER, " +
                    ICON_URL + " TEXT, " +
                    CITY_ID + " INTEGER);")
        }

    }

    //wxm:升级库
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

        //wxm:删除表
        db.dropTable(CityForecastTable.NAME, true)
        db.dropTable(DayForecastTable.NAME, true)

        //wxm:创建表
        onCreate(db)
    }
}