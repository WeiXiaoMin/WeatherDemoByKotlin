package com.bmbm.weatherdemobykotlin.extensions

import java.util.*

/**
 * 集合相关扩展函数
 * Created by sjsd on 2017/6/8.
 */

//wxm:out 修饰符表明 Array<out Pair<K, V>>用于生产，而不用与消费（不允许调用add或set方法）
//wxm:则该类型是协变型（covariant）的，如 Array<Pair<Any,Any>> 可作为 Array<Pair<String,Int>>的超类
fun <K, V> MutableMap<K, V?>.toVarargArray(): Array<out Pair<K, V>>
        = map({ Pair(it.key, it.value!!) }).toTypedArray()


/**
 * 遍历集合，根据传入的函数对集合中的元素处理后获得结果，并返回第一个不为空的结果
 * 与Iterable<T>.first的区别是返回的是结果而不是元素
 */
fun <T, R> Iterable<T>.firstResult(predicate: (T) -> R?): R {
    for (element in this) {
        val result = predicate(element)
        if (result != null) return result
    }
    throw NoSuchElementException("No element matching predicate was found.")
}