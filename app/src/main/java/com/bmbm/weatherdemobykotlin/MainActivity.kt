package com.bmbm.weatherdemobykotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    private val items = listOf(
            "Mon	6/23	-	Sunny	-	31/17",
            "Tue	6/24	-	Foggy	-	21/8",
            "Wed	6/25	-	Cloudy	-	22/17",
            "Thurs	6/26	-	Rainy	-	18/11",
            "Fri	6/27	-	Foggy	-	21/10",
            "Sat	6/28	-	TRAPPED	IN	WEATHERSTATION	-	23/18",
            "Sun	6/29	-	Sunny	-	20/7")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rv_container = findViewById(R.id.rv_container) as RecyclerView
//        val rv_container: RecyclerView = find(R.id.rv_container) //Anko库中的方法
        rv_container.layoutManager = LinearLayoutManager(this)
        rv_container.adapter = ForecastListAdapter(items)

        val url="http://www.sojson.com/open/api/weather/json.shtml?city=北京"
        async {
            Log.d(javaClass.simpleName,"run=============")
            Request(url).run()
            uiThread {
                loadingDialog("请求成功").show()
            }
        }

    }



}
