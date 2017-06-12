package com.bmbm.weatherdemobykotlin.extensions

import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.SelectQueryBuilder

/**
 * 有关数据库的扩展函数
 * Created by sjsd on 2017/6/8.
 */

//wxm:重载SelectQueryBuilder的parseList方法，指定MapRowParser作为参数，避免和RowParser冲突
/**
 * 使用MapRowParser解析器解析对象集合
 */
fun <T : Any> SelectQueryBuilder.parseList(parse: (Map<String, Any?>) -> T): List<T> =
        parseList(object : MapRowParser<T> {    //wxm:map解析器对象
            override fun parseRow(columns: Map<String, Any?>): T = parse(columns)
        })

/**
 * 使用MapRowParser解析器解析对象
 */
fun <T : Any> SelectQueryBuilder.parseOpt(parse: (Map<String, Any?>) -> T): T? =
        parseOpt(object : MapRowParser<T> { //wxm:map解析器对象
            override fun parseRow(columns: Map<String, Any?>): T = parse(columns)
        })

/**
 * 使用id查询数据库，使用条件：数据库中的id字段为“_id”
 */
fun SelectQueryBuilder.byId(id: Long): SelectQueryBuilder = whereSimple("_id = ?", id.toString())