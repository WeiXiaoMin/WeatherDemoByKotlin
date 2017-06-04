package com.bmbm.weatherdemobykotlin.test

/**
 * Created by wxm on 2017/6/4.
 */

class Forecast {

    var city: CityBean? = null
    var cod: String? = null
    var message: Double = 0.toDouble()
    var cnt: Int = 0
    var list: List<ListBean>? = null

    class CityBean {
        var id: Int = 0
        var name: String? = null
        var coord: CoordBean? = null
        var country: String? = null
        var population: Int = 0

        class CoordBean {
            var lon: Double = 0.toDouble()
            var lat: Double = 0.toDouble()
        }
    }

    class ListBean {
        var dt: Int = 0
        var temp: TempBean? = null
        var pressure: Double = 0.toDouble()
        var humidity: Int = 0
        var speed: Double = 0.toDouble()
        var deg: Int = 0
        var clouds: Int = 0
        var rain: Double = 0.toDouble()
        var weather: List<WeatherBean>? = null

        class TempBean {
            var day: Double = 0.toDouble()
            var min: Double = 0.toDouble()
            var max: Double = 0.toDouble()
            var night: Double = 0.toDouble()
            var eve: Double = 0.toDouble()
            var morn: Double = 0.toDouble()
        }

        class WeatherBean {
            var id: Int = 0
            var main: String? = null
            var description: String? = null
            var icon: String? = null
        }
    }
}
