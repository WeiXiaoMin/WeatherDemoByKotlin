package com.bmbm.weatherdemobykotlin.domain

/**
 * Created by wxm on 2017/6/4.
 */
interface Command<T> {

    fun execute():T

}