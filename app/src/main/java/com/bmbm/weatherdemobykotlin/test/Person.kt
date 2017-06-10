package com.bmbm.weatherdemobykotlin.test

/**
 * 属性示例
 * 默认已经含get、set方法，如果需要重写，则get、set方法写在属性下方，方法体中使用预留字段field代替该属性
 * Created by wxm on 2017/6/4.
 */
class Person {
    var name: String = ""
        get() = field.toUpperCase()
        set(value) {
            field = "name:$value"
        }

    var age: Int = 0
        get() = field
        set(value) {
            field = value
        }
}